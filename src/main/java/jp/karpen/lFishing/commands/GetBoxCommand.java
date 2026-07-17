package jp.karpen.lFishing.commands;

import jp.karpen.lFishing.LFishing;
import jp.karpen.lFishing.models.BoxType;
import jp.karpen.lFishing.utils.LangManager;
import jp.karpen.lFishing.utils.SkinManager;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GetBoxCommand implements CommandExecutor {

    private final SkinManager skinManager;
    private final Configuration config;
    private final LangManager lang;

    public GetBoxCommand(LFishing plugin, SkinManager skinManager){
        this.skinManager = skinManager;
        this.config = plugin.getConfig();
        this.lang = plugin.getLangManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length != 1){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    Objects.requireNonNull(lang.getMessage("msg.using"))));

            return true;
        }

        if (!(sender instanceof Player player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    Objects.requireNonNull(lang.getMessage("msg.console"))));

            return true;
        }

        switch (args[0].toLowerCase()){
            case "default" -> dropBox(player, BoxType.DEFAULT);
            case "normal" -> dropBox(player, BoxType.NORMAL);
            case "epic" -> dropBox(player, BoxType.EPIC);
            case "legend" -> dropBox(player, BoxType.LEGEND);
            default -> {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Objects.requireNonNull(lang.getMessage("msg.using"))));

                return true;
            }
        }

        return true;
    }

    private void dropBox(Player player, BoxType type) {
        ItemStack item = skinManager.getHead(config.getString(String.format("skin.%s", type.toString().toLowerCase())));
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            String formattedType = String.format("%s_box", type.toString().toLowerCase());
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', 
                    Objects.requireNonNull(lang.getMessage(String.format("names.%s", type.toString().toLowerCase())))));
            NamespacedKey key = new NamespacedKey("lfishing", formattedType);
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, formattedType);
            item.setItemMeta(meta);
        }

        player.getWorld().dropItem(player.getLocation(), item);

    }
}
