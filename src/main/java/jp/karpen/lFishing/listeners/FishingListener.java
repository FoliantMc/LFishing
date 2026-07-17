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
import org.bukkit.*;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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

    public FishingListener(LFishing plugin, SkinManager skinManager) {
        this.config = plugin.getConfig();
        this.lang = plugin.getLangManager();
        this.skinManager = skinManager;

        this.defaultBox = new DefaultBox(plugin);
        this.normalBox = new NormalBox(plugin);
        this.epicBox = new EpicBox(plugin);
        this.legendBox = new LegendBox(plugin);
    }

    @EventHandler
    public void onPlayerFishing(PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;

        Player player = event.getPlayer();

        if (ThreadLocalRandom.current().nextDouble(0, 500) < config.getDouble("chances.break")) {
            if (player.getInventory().getItemInMainHand().getType() != Material.FISHING_ROD) return;
            player.getInventory().getItemInMainHand().setAmount(0);
            player.playSound(player.getLocation(), Sound.AMBIENT_WARPED_FOREST_MOOD, 10.0f, 1.0f);
            return;
        }

        double defaultChance = getChance(BoxType.DEFAULT, player);
        double normalChance = getChance(BoxType.NORMAL, player);
        double epicChance = getChance(BoxType.EPIC, player);
        double legendChance = getChance(BoxType.LEGEND, player);
        double totalChance = defaultChance + normalChance + epicChance + legendChance;
        double random = Math.random() * totalChance;

        double cumulative = 0;
        cumulative += legendChance;
        if (random < cumulative) {
            legendBox.dropBox(player, skinManager);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', 
                    lang.getMessage("msg.legend")));
            return;
        }

        cumulative += epicChance;
        if (random < cumulative) {
            epicBox.dropBox(player, skinManager);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', 
                    lang.getMessage("msg.epic")));
            return;
        }

        cumulative += normalChance;
        if (random < cumulative) {
            normalBox.dropBox(player, skinManager);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', 
                    lang.getMessage("msg.normal")));
            return;
        }

        cumulative += defaultChance;
        if (random < cumulative) {
            defaultBox.dropBox(player, skinManager);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', 
                    lang.getMessage("msg.default")));
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
        return config.getDouble(String.format(isLuckRod(player) ? "luck.%s" : "chances.%s", type.toString().toLowerCase()));
    }

    private static boolean isLuckRod(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        return meta != null && item.getType().equals(Material.FISHING_ROD) && meta.hasEnchant(Enchantment.LUCK_OF_THE_SEA);
    }
}
