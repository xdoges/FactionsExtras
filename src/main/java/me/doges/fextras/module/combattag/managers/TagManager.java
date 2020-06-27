package me.doges.fextras.module.combattag.managers;

import me.doges.fextras.manager.Manager;
import me.doges.fextras.manager.ManagerHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class TagManager extends Manager {

    private List<UUID> loggers = new ArrayList<>();
    private Map<UUID, Long> tagged = new HashMap<>();

    public TagManager(ManagerHandler managerHandler) {
        super(managerHandler);
    }

    public void addLogger(UUID player) {
        loggers.add(player);
    }

    public boolean isLogger(Player player) {
        return loggers.contains(player.getUniqueId());
    }

    public void tagPlayer(Player player, String message) {
        tagged.put(player.getUniqueId(), System.currentTimeMillis());

        if (message != null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    public void handleLogger(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setHealth(0);
        loggers.remove(player.getUniqueId());
    }

    public boolean isTagged(Player player) {
        return tagged.containsKey(player.getUniqueId()) && !isTagExpired(player.getUniqueId());
    }

    public int getRemainingTime(Player player) {
        return (int) (managerHandler.getPlugin().getSettings().getCombatTagSettings().getCombatTagTime() - (System.currentTimeMillis() - tagged.get(player.getUniqueId())) / 1000L);
    }

    private boolean isTagExpired(UUID player) {
        return System.currentTimeMillis() - tagged.get(player) >= managerHandler.getPlugin().getSettings().getCombatTagSettings().getCombatTagTime() * 1000;
    }
}
