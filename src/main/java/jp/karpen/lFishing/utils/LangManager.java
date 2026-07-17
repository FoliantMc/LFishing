package jp.karpen.lFishing.utils;

import jp.karpen.lFishing.LFishing;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LangManager {
    private final LFishing plugin;
    private FileConfiguration langConfig = null;
    private File langFile = null;

    public LangManager(LFishing plugin) {
        this.plugin = plugin;
        saveDefaultLangs();
        reloadLang();
    }

    public void reloadLang() {
        String langName = plugin.getConfig().getString("lang", "en_US");
        if (langFile == null) {
            File langDir = new File(plugin.getDataFolder(), "langs");
            if (!langDir.exists()) {
                langDir.mkdirs();
            }
            langFile = new File(langDir, langName + ".yml");
        }

        if (!langFile.getName().equals(langName + ".yml")) {
            langFile = new File(new File(plugin.getDataFolder(), "langs"), langName + ".yml");
        }

        if (!langFile.exists()) {
            plugin.saveResource("langs/" + langName + ".yml", false);
        }

        langConfig = YamlConfiguration.loadConfiguration(langFile);

        InputStream defaultLangStream = plugin.getResource("langs/en_US.yml");
        if (defaultLangStream != null) {
            YamlConfiguration defaultLangConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultLangStream, StandardCharsets.UTF_8));
            langConfig.setDefaults(defaultLangConfig);
        }
    }

    public FileConfiguration getLangConfig() {
        if (langConfig == null) {
            reloadLang();
        }
        return langConfig;
    }

    public String getMessage(String path) {
        return getLangConfig().getString(path, "Missing lang: " + path);
    }

    private void saveDefaultLangs() {
        File langDir = new File(plugin.getDataFolder(), "langs");
        if (!langDir.exists()) {
            langDir.mkdirs();
        }
        
        File defaultFile = new File(langDir, "en_US.yml");
        if (!defaultFile.exists()) {
            plugin.saveResource("langs/en_US.yml", false);
        }
    }
}
