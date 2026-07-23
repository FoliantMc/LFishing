package jp.karpen.lFishing.boxes;

import jp.karpen.lFishing.LFishing;
import jp.karpen.lFishing.models.BoxType;
import jp.karpen.lFishing.utils.LangManager;
import jp.karpen.lFishing.utils.SkinManager;
import org.bukkit.*;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractBox implements Listener {
    protected final LFishing plugin;
    protected final Configuration config;
    protected final LangManager lang;
    protected final BoxType boxType;

    private final Map<Player, Inventory> playerInventories;

    public AbstractBox(LFishing plugin, BoxType boxType) {
        this.boxType = boxType;

        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.lang = plugin.getLangManager();
        this.playerInventories = new HashMap<>();

        plugin.registerListener(this);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (playerInventories.containsKey(player)){
            Inventory inventory = playerInventories.get(player);

            for (ItemStack item : inventory.getContents()) {
                if (item != null && item.getType() != Material.AIR) {
                    HashMap<Integer, ItemStack> excess = player.getInventory().addItem(item);
                    for (ItemStack excessItem : excess.values()) {
                        player.getWorld().dropItemNaturally(player.getLocation(), excessItem);
                    }
                }
            }

            playerInventories.remove(player);
        }
    }

    public void openBox(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 27,
                ChatColor.translateAlternateColorCodes('&',
                        Objects.requireNonNull(lang.getMessage(String.format("names.%s", boxType.toString().toLowerCase())))));

        playerInventories.put(player, inventory);

        List<ItemStack> items = generateRandomItems();

        for (ItemStack item : items) {
            int slot;
            do {
                slot = ThreadLocalRandom.current().nextInt(0, 27);
            } while (inventory.getItem(slot) != null);

            inventory.setItem(slot, item);
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (isThisBox(itemInHand)) {
            player.getInventory().getItemInMainHand().setAmount(itemInHand.getAmount() - 1);
        }

        player.playSound(player.getLocation(), Sound.UI_LOOM_TAKE_RESULT, 1.0f, 1.0f);

        Random random = ThreadLocalRandom.current();;
        int spawnChance = random.nextInt(0, 10);

        if (spawnChance < 1) {
            int count = random.nextInt(1, 5);
            for (int i = 0; i < count; i++) {
                player.getWorld().spawnEntity(player.getLocation(), EntityType.SILVERFISH);
            }
        } else {
            player.openInventory(inventory);
        }
    }

    protected abstract List<ItemStack> generateRandomItems();

    public boolean isThisBox(ItemStack item) {
        if (item == null || item.getType() != Material.PLAYER_HEAD) return false;

        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            String formatted = String.format("%s_box", boxType.toString().toLowerCase());
            NamespacedKey key = new NamespacedKey("lfishing", formatted);
            return meta.getPersistentDataContainer().has(key, PersistentDataType.STRING) &&
                    formatted.equals(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));

        }

        return false;
    }

    public void dropBox(Player player, SkinManager skinManager) {
        ItemStack item = skinManager.getHead(config.getString(String.format("skin.%s", boxType.toString().toLowerCase())));
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            String formattedType = String.format("%s_box", boxType.toString().toLowerCase());

            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                    Objects.requireNonNull(lang.getMessage(String.format("names.%s", boxType.toString().toLowerCase())))));
            NamespacedKey key = new NamespacedKey("lfishing", formattedType);
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, formattedType);
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);
    }
}
