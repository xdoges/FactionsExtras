package me.doges.fextras.settings.impl;

import me.doges.fextras.FactionsExtras;
import me.doges.fextras.settings.ISettings;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CombatTagSettings implements ISettings {

    public final String PREFIX = "combat-tag-";

    public String DAMAGER_MESSAGE;
    public String DAMAGED_MESSAGE;

    public int COMBAT_TAG_TIME;
    public int NPC_DESPAWN_TIME;

    private List<World> DISABLED_WORLDS = new ArrayList<>();

    private final FactionsExtras plugin;

    public CombatTagSettings(FactionsExtras plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        DAMAGER_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(PREFIX + "damager-message"));
        DAMAGED_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(PREFIX + "damaged-message"));

        COMBAT_TAG_TIME = plugin.getConfig().getInt(PREFIX + "expire-delay");
        NPC_DESPAWN_TIME = plugin.getConfig().getInt(PREFIX + "npc-despawn-time");

        DISABLED_WORLDS.addAll(plugin.getConfig().getStringList(PREFIX + "disabled-worlds")
                .stream()
                .map(disabled -> plugin.getServer().getWorld(disabled))
                .collect(Collectors.toList()));
    }

    public String getDamagerMessage(Player player) {
        return DAMAGER_MESSAGE.replace("%player%", player.getName()).replace("%time%", String.valueOf(getCombatTagTime()));
    }

    public String getDamagedMessage(Player player) {
        return DAMAGED_MESSAGE.replace("%player%", player.getName()).replace("%time%", String.valueOf(getCombatTagTime()));
    }

    public int getCombatTagTime() {
        return COMBAT_TAG_TIME;
    }

    public int getNPCDespawnTime() {
        return NPC_DESPAWN_TIME;
    }

    public List<World> getDisabledWorlds() {
        return DISABLED_WORLDS;
    }
}
