package me.doges.fextras.listener.listeners;

import me.doges.fextras.FactionsExtras;
import me.doges.fextras.settings.impl.MiscSettings;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BowBoostingListener implements Listener {

    private final FactionsExtras plugin;
    private final MiscSettings settings;

    public BowBoostingListener(FactionsExtras plugin) {
        this.plugin = plugin;
        this.settings = plugin.getSettings().getMiscSettings();
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getDamager();

            if (arrow.getShooter() instanceof Player && event.getEntity() instanceof Player) {
                Player shooter = (Player) arrow.getShooter();
                Player victim = (Player) event.getEntity();

                if (shooter.getUniqueId().equals(victim.getUniqueId())) {
                    if (settings.isBowBoostingDisabled()) {
                        event.setCancelled(true);
                        shooter.sendMessage(settings.getBowBoostDisabledMessage());
                    }
                }
            }
        }
    }
}
