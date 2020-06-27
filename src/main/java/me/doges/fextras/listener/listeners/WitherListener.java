package me.doges.fextras.listener.listeners;

import me.doges.fextras.FactionsExtras;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class WitherListener implements Listener {

    private final FactionsExtras plugin;

    public WitherListener(FactionsExtras plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getEntity().getType() == EntityType.WITHER && plugin.getSettings().getMiscSettings().isWithersDisabled()) {
            event.setCancelled(true);
        }
    }
}
