package jp.karpen.lFishing;

import jp.karpen.lFishing.commands.GetBoxCommand;
import jp.karpen.lFishing.commands.GetBoxCompleter;
import jp.karpen.lFishing.listeners.FishingListener;
import jp.karpen.lFishing.utils.LangManager;
import jp.karpen.lFishing.utils.SkinManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class LFishing extends JavaPlugin {

    @Getter
    private static LFishing instance;

    @Getter
    private static LangManager langManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        langManager = new LangManager(this);

        SkinManager skinManager = new SkinManager();
        FishingListener fishingListener = new FishingListener(this, skinManager);

        registerCommands(new GetBoxCommand(this, skinManager), new GetBoxCompleter(), "getBox");
        registerListener(fishingListener);
    }

    public <L extends Listener> void registerListener(L listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    private <C extends CommandExecutor, T extends TabCompleter> void registerCommands(C command, T tabCompleter, String name) {
        Objects.requireNonNull(getCommand(name)).setExecutor(command);
        Objects.requireNonNull(getCommand(name)).setTabCompleter(tabCompleter);
    }

    public static JavaPlugin getInstance() {
        return getInstance();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
