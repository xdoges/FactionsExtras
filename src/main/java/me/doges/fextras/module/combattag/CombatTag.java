package me.doges.fextras.module.combattag;

import me.doges.fextras.FactionsExtras;
import me.doges.fextras.module.combattag.managers.NPCManager;
import me.doges.fextras.module.combattag.managers.TagManager;
import me.doges.fextras.module.combattag.npc.NPC;
import me.doges.fextras.module.Module;
import me.doges.fextras.settings.impl.CombatTagSettings;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class CombatTag extends Module {

    private CombatTagSettings settings;
    private NPCManager npcManager;
    private TagManager tagManager;

    public CombatTag(FactionsExtras plugin) {
        super(plugin, "combat-tag");

        this.settings = plugin.getSettings().getCombatTagSettings();
        this.npcManager = plugin.getManagerHandler().getNpcManager();
        this.tagManager = plugin.getManagerHandler().getTagManager();
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void handleTag(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            // If the config value is set to -1 or the world the entity was damaged in is in our list, we do nothing
            if (settings.getCombatTagTime() == -1 || settings.getDisabledWorlds().contains(event.getEntity().getWorld())) {
                return;
            }

            // Handle the tags
            Player damager = (Player) event.getDamager();
            Player damaged = (Player) event.getEntity();
            tagManager.tagPlayer(damager, tagManager.isTagged(damager) ? null : settings.getDamagerMessage(damaged));
            tagManager.tagPlayer(damaged, tagManager.isTagged(damaged) ? null : settings.getDamagedMessage(damager));
        } else if (event.getEntity() instanceof Zombie && event.getDamager() instanceof Player) {
            // In this context, the damaged would be a player's NPC
            NPC npc = npcManager.fromZombie((Zombie) event.getEntity());

            // We're going to reset the NPC's task since the NPC got damaged
            // We don't want the NPC to disappear while players are trying to kill it
            if (npc != null) {
                npcManager.resetTask(npc);
            }
        } else if (event.getEntity() instanceof Player && event.getDamager() instanceof Projectile && ((Projectile) event.getDamager()).getShooter() instanceof Player) {
            // Handle tagging by projectiles

            // Enderpearls are projectiles that can damage players, we're not going to count them as tags
            if (event.getDamager() instanceof EnderPearl) {
                return;
            }

            Player damager = (Player) ((Projectile) event.getDamager()).getShooter();
            Player damaged = (Player) event.getEntity();

            // If the config value is set to -1 or the world the entity was damaged in is in our list, we do nothing
            if (settings.getCombatTagTime() == -1 || settings.getDisabledWorlds().contains(event.getEntity().getWorld())) {
                return;
            }

            tagManager.tagPlayer(damager, tagManager.isTagged(damager) ? null : settings.getDamagerMessage(damaged));
            tagManager.tagPlayer(damaged, tagManager.isTagged(damaged) ? null : settings.getDamagedMessage(damager));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player && event.getEntity().getKiller() != null) {
            tagManager.tagPlayer((Player) event.getEntity(), null);
        }

        if (event.getEntity() instanceof Zombie) {
            NPC npc = npcManager.fromZombie((Zombie) event.getEntity());

            if (npc != null) {
                event.getDrops().clear();
                event.setDroppedExp(0);
                npcManager.handleDeath(npc);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handlePlayerJoin(PlayerJoinEvent event) {
        NPC npc = npcManager.fromPlayer(event.getPlayer());

        if (npc != null) {
            event.getPlayer().teleport(npc.getEntity().getLocation());
            npcManager.removeNPC(npc);
        } else if (tagManager.isLogger(event.getPlayer())) {
            tagManager.handleLogger(event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handlePlayerQuit(PlayerQuitEvent event) {
        if (event.getPlayer().hasPermission("fextras.combattag.exempt") || settings.getDisabledWorlds().contains(event.getPlayer().getWorld())) {
            return;
        }

        npcManager.spawnNPC(event.getPlayer());
    }

    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent event) {
        for (Entity entity : event.getChunk().getEntities()) {
            if (entity instanceof Zombie && npcManager.fromZombie((Zombie) entity) != null) {
                entity.remove();
            }
        }
    }
}
