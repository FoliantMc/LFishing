package jp.karpen.lFishing.listeners;

import jp.karpen.lFishing.LFishing;
import jp.karpen.lFishing.boxes.*;
import jp.karpen.lFishing.boxes.DefaultBox;
import jp.karpen.lFishing.boxes.EpicBox;
import jp.karpen.lFishing.boxes.LegendBox;
import jp.karpen.lFishing.boxes.NormalBox;
import jp.karpen.lFishing.models.BoxType;
import jp.karpen.lFishing.utils.LangManager;
import jp.karpen.lFishing.utils.SkinManager;
import jp.karpen.lFishing.minigame.FishingMinigame;
import org.bukkit.*;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.concurrent.ThreadLocalRandom;

public class FishingListener implements Listener {
    private final Configuration config;
    private final LangManager lang;
    private final SkinManager skinManager;

    private final DefaultBox defaultBox;
    private final NormalBox normalBox;
    private final EpicBox epicBox;
    private final LegendBox legendBox;
    private final FishingMinigame minigame;

    public FishingListener(LFishing plugin, SkinManager skinManager) {
        this.config = plugin.getConfig();
        this.lang = LFishing.getLangManager();
        this.skinManager = skinManager;

        this.defaultBox = new DefaultBox(plugin);
        this.normalBox = new NormalBox(plugin);
        this.epicBox = new EpicBox(plugin);
        this.legendBox = new LegendBox(plugin);
        this.minigame = new FishingMinigame(plugin, skinManager);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerFishing(PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;
        if (event.getCaught() == null) return;

        Player player = event.getPlayer();

        event.getHook().setWaitTime(10, 10);

        final org.bukkit.entity.Entity caught = event.getCaught();

        ItemStack caughtItem = null;
        if (caught instanceof Item) {
            caughtItem = ((Item) caught).getItemStack().clone();
        }

        caught.remove();

        final ItemStack finalCaughtItem = caughtItem;

        minigame.start(player, () -> {
            if (ThreadLocalRandom.current().nextDouble(100) < config.getDouble("chances.break")) {
                if (player.getInventory().getItemInMainHand().getType() == Material.FISHING_ROD) {
                    player.getInventory().getItemInMainHand().setAmount(0);
                    player.playSound(player.getLocation(), Sound.AMBIENT_WARPED_FOREST_MOOD, 10.0f, 1.0f);
                }
                return;
            }

            if (ThreadLocalRandom.current().nextDouble(100) <= config.getDouble("chances.drop")) {
                handleBoxDrop(player);
            } else {
                if (finalCaughtItem != null) {
                    player.getInventory().addItem(finalCaughtItem).values().forEach(item -> 
                        player.getWorld().dropItemNaturally(player.getLocation(), item));
                }
            }
        }, () -> {});
    }

    private void handleBoxDrop(Player player) {
        double defaultChance = getChance(BoxType.DEFAULT, player);
        double normalChance = getChance(BoxType.NORMAL, player);
        double epicChance = getChance(BoxType.EPIC, player);
        double legendChance = getChance(BoxType.LEGEND, player);
        double totalChance = defaultChance + normalChance + epicChance + legendChance;

        if (totalChance <= 0) return;

        double random = ThreadLocalRandom.current().nextDouble(totalChance);
        double cumulative = 0;

        cumulative += legendChance;
        if (random < cumulative) {
            legendBox.dropBox(player, skinManager);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getMessage("msg.legend")));
            return;
        }

        cumulative += epicChance;
        if (random < cumulative) {
            epicBox.dropBox(player, skinManager);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getMessage("msg.epic")));
            return;
        }

        cumulative += normalChance;
        if (random < cumulative) {
            normalBox.dropBox(player, skinManager);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getMessage("msg.normal")));
            return;
        }

        cumulative += defaultChance;
        if (random < cumulative) {
            defaultBox.dropBox(player, skinManager);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getMessage("msg.default")));
        }
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();

            if (defaultBox.isThisBox(player.getInventory().getItemInMainHand())) {
                defaultBox.openBox(player);

                event.setCancelled(true);
            }

            if (epicBox.isThisBox(player.getInventory().getItemInMainHand())) {
                epicBox.openBox(player);

                event.setCancelled(true);
            }

            if (normalBox.isThisBox(player.getInventory().getItemInMainHand())) {
                normalBox.openBox(player);

                event.setCancelled(true);
            }

            if (legendBox.isThisBox(player.getInventory().getItemInMainHand())) {
                legendBox.openBox(player);

                event.setCancelled(true);
            }
        }
    }

    private double getChance(BoxType type, Player player) {
        boolean useLuck = config.getBoolean("luck.enabled") && isLuckRod(player);
        return config.getDouble(String.format(useLuck ? "luck.%s" : "chances.%s", type.toString().toLowerCase()));
    }

    private static boolean isLuckRod(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        return meta != null && item.getType().equals(Material.FISHING_ROD) && meta.hasEnchant(Enchantment.LUCK_OF_THE_SEA);
    }
}
