package me.doges.fextras.listener.listeners;

import me.doges.fextras.FactionsExtras;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener {

    private final FactionsExtras plugin;

    public CreatureSpawnListener(FactionsExtras plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM && plugin.getSettings().getMiscSettings().isNaturalMobSpawningDisabled()) {
            event.setCancelled(true);
        }
    }
}
