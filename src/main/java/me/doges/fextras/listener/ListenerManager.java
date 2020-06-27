package me.doges.fextras.listener;

import me.doges.fextras.FactionsExtras;
import me.doges.fextras.listener.listeners.*;
import me.doges.fextras.manager.Manager;
import me.doges.fextras.manager.ManagerHandler;

import java.util.stream.Stream;

public class ListenerManager extends Manager {

    private FactionsExtras plugin;

    public ListenerManager(ManagerHandler managerHandler) {
        super(managerHandler);
        this.plugin = managerHandler.getPlugin();
    }

    public void registerListeners() {
        Stream.of(
                new CraftListener(plugin),
                new WitherListener(plugin),
                new WeatherListener(plugin),
                new CreatureSpawnListener(plugin),
                new BowBoostingListener(plugin)
        ).forEach(listener -> managerHandler.getPlugin().getServer().getPluginManager().registerEvents(listener, managerHandler.getPlugin()));
    }
}
