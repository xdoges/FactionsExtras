package me.doges.fextras.settings.impl;

import me.doges.fextras.FactionsExtras;
import me.doges.fextras.settings.ISettings;
import org.bukkit.ChatColor;

public final class CooldownSettings implements ISettings {

    public String ENDERPEARL_COOLDOWN_MESSAGE;
    public String GOLDEN_APPLE_COOLDOWN_MESSAGE;
    public String NOTCH_APPLE_COOLDOWN_MESSAGE;

    public int ENDERPEARL_COOLDOWN_TIME;
    public int GOLDEN_APPLE_COOLDOWN_TIME;
    public int NOTCH_APPLE_COOLDOWN_TIME;

    private final FactionsExtras plugin;

    public CooldownSettings(FactionsExtras plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        ENDERPEARL_COOLDOWN_MESSAGE = plugin.getConfig().getString("enderpearl-cooldown-message");
        GOLDEN_APPLE_COOLDOWN_MESSAGE = plugin.getConfig().getString("golden-apple-cooldown-message");
        NOTCH_APPLE_COOLDOWN_MESSAGE = plugin.getConfig().getString("notch-apple-cooldown-message");

        ENDERPEARL_COOLDOWN_TIME = plugin.getConfig().getInt("enderpearl-cooldown-time");
        GOLDEN_APPLE_COOLDOWN_TIME = plugin.getConfig().getInt("golden-apple-cooldown-time");
        NOTCH_APPLE_COOLDOWN_TIME = plugin.getConfig().getInt("notch-apple-cooldown-time");
    }

    public String getEnderpearlCooldownMessage(long timeLeft) {
        return color(ENDERPEARL_COOLDOWN_MESSAGE).replace("%time%", String.valueOf(timeLeft / 1000));
    }

    public String getGoldenAppleCooldownMessage(long timeLeft) {
        return color(GOLDEN_APPLE_COOLDOWN_MESSAGE).replace("%time%", String.valueOf(timeLeft / 1000));
    }

    public String getNotchAppleCooldownMessage(long timeLeft) {
        return color(NOTCH_APPLE_COOLDOWN_MESSAGE).replace("%time%", String.valueOf(timeLeft / 1000));
    }

    public int getEnderpearlCooldownTime() {
        return ENDERPEARL_COOLDOWN_TIME;
    }

    public int getGoldenAppleCooldownTime() {
        return GOLDEN_APPLE_COOLDOWN_TIME;
    }

    public int getNotchAppleCooldownTime() {
        return NOTCH_APPLE_COOLDOWN_TIME;
    }

    private String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
