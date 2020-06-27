package me.doges.fextras.listener.listeners;

import me.doges.fextras.FactionsExtras;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {

    private final FactionsExtras plugin;

    public WeatherListener(FactionsExtras plugin) {
        this.plugin = plugin;

        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (World world : plugin.getServer().getWorlds()) {
                    world.setTime(0);
                }
            }
        }, 0L, 100000);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}
