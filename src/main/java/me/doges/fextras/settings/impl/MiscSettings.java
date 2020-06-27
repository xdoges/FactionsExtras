package me.doges.fextras.settings.impl;

import me.doges.fextras.FactionsExtras;
import me.doges.fextras.settings.ISettings;
import org.bukkit.ChatColor;

public class MiscSettings implements ISettings {

    private boolean WITHERS_DISABLED;
    private boolean NATURAL_MOB_SPAWNING;
    private boolean BOW_BOOSTING;

    private String BOW_BOOSTING_MESSAGE;


    private final FactionsExtras plugin;

    public MiscSettings(FactionsExtras plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        WITHERS_DISABLED = plugin.getConfig().getBoolean("disable-wither-spawning");
        NATURAL_MOB_SPAWNING = plugin.getConfig().getBoolean("disable-natural-mob-spawning");
        BOW_BOOSTING = plugin.getConfig().getBoolean("disable-bow-boosting");

        BOW_BOOSTING_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("bow-boosting-message"));
    }

    public boolean isWithersDisabled() {
        return WITHERS_DISABLED;
    }

    public boolean isNaturalMobSpawningDisabled() {
        return NATURAL_MOB_SPAWNING;
    }

    public boolean isBowBoostingDisabled() {
        return BOW_BOOSTING;
    }

    public String getBowBoostDisabledMessage() {
        return BOW_BOOSTING_MESSAGE;
    }
}
