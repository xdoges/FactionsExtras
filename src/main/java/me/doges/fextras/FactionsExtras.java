package me.doges.fextras;

import lombok.Getter;
import me.doges.fextras.command.CommandHandler;
import me.doges.fextras.manager.ManagerHandler;
import me.doges.fextras.settings.Settings;
import org.bukkit.plugin.java.JavaPlugin;

public final class FactionsExtras extends JavaPlugin {

    @Getter private static FactionsExtras instance;

    private ConfigValues c;

    @Getter private ManagerHandler managerHandler;
    @Getter private Settings settings;

    @Override
    public void onEnable() {
        instance = this;

        c = new ConfigValues(this);
        managerHandler = new ManagerHandler(this);

        settings = new Settings(this);
        settings.load();

        managerHandler.load();

        new CommandHandler(this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ConfigValues c() {
        return c;
    }
}
