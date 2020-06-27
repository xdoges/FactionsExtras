package me.doges.fextras.module.jellylegs;

import lombok.Getter;
import me.doges.fextras.FactionsExtras;
import me.doges.fextras.module.Module;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JellyLegs extends Module {

    @Getter private List<UUID> users = new ArrayList<>();

    public JellyLegs(FactionsExtras plugin) {
        super(plugin, "jelly-legs");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && users.contains(event.getEntity().getUniqueId())) {
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (users.contains(event.getPlayer().getUniqueId())) {
            users.remove(event.getPlayer().getUniqueId());
        }
    }
}
