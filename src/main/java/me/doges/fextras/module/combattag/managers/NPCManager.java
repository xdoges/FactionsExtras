package me.doges.fextras.module.combattag.managers;

import me.doges.fextras.module.combattag.npc.NPC;
import me.doges.fextras.manager.Manager;
import me.doges.fextras.manager.ManagerHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class NPCManager extends Manager {

    private HashMap<NPC, BukkitTask> taskMap = new HashMap<>();

    public NPCManager(ManagerHandler managerHandler) {
        super(managerHandler);
    }

    public void spawnNPC(Player player) {
        NPC npc = new NPC(player);
        taskMap.put(npc, newNPCTask(npc));
    }

    public void removeNPC(NPC npc) {
        if (npc.getEntity() != null && !npc.getEntity().isDead()) {
            npc.getEntity().remove();
        }

        if (taskMap.containsKey(npc)) {
            taskMap.remove(npc);
        }
    }

    public void handleDeath(NPC npc) {
        for (ItemStack i : npc.getInventoryContents()) {
            if (i != null && i.getType() != Material.AIR) {
                npc.getEntity().getWorld().dropItem(npc.getEntity().getLocation(), i);
            }
        }
        managerHandler.getTagManager().addLogger(npc.getLogger());
        removeNPC(npc);
    }

    public NPC fromZombie(Zombie zombie) {
        for (NPC npc : taskMap.keySet()) {
            if (npc.isNPC(zombie)) {
                return npc;
            }
        }
        return null;
    }

    public NPC fromPlayer(Player player) {
        for (NPC npc : taskMap.keySet()) {
            if (npc.getLogger().equals(player.getUniqueId())) {
                return npc;
            }
        }
        return null;
    }

    public void resetTask(NPC npc) {
        if (taskMap.containsKey(npc)) {
            taskMap.get(npc).cancel();
        }
        taskMap.put(npc, newNPCTask(npc));
    }

    private BukkitTask newNPCTask(NPC npc) {
        return Bukkit.getScheduler().runTaskLater(managerHandler.getPlugin(), new Runnable() {
            @Override
            public void run() {
                NPCManager.this.removeNPC(npc);
            }
        }, managerHandler.getPlugin().getSettings().getCombatTagSettings().getNPCDespawnTime() * 20);
    }
}
