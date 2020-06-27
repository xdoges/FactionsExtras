package me.doges.fextras;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigValues {

    protected final FactionsExtras plugin;
    protected final File dir;

    private FileConfiguration config;
    private FileConfiguration messages;
    private FileConfiguration modules;

    ConfigValues(FactionsExtras plugin) {
        this.plugin = plugin;
        this.dir = new File(plugin.getDataFolder().getAbsolutePath());

        plugin.saveDefaultConfig();

        this.config = plugin.getConfig();
        this.messages = config("messages");
        this.modules = config("modules");
    }

    public String getMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', messages.getString(message));
    }

    public boolean isModuleEnabled(String moduleName) {
        return modules.getBoolean(moduleName);
    }

    private FileConfiguration config(String fileName) {
        File file = new File(dir, fileName + ".yml");
        if (!file.exists()) {
            plugin.saveResource(file.getName(), false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }
}
