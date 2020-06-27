package me.doges.fextras.settings.impl;

import me.doges.fextras.FactionsExtras;
import me.doges.fextras.settings.ISettings;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MessageSettings implements ISettings {

    // Generic
    private String NO_PERMISSION;
    private String CONSOLE_ONLY_COMMAND;
    private String PLAYER_ONLY_COMMAND;
    private String PLAYER_NOT_FOUND;
    private String COMMAND_NOT_ENABLED;

    // Command specific
    private String PING_SELF_MESSAGE;
    private String PING_TARGET_MESSAGE;

    private String COMBAT_TAG_NOT_IN_COMBAT;
    private String COMBAT_TAG_SELF_MESSAGE;
    private String COMBAT_TAG_OTHER_MESSAGE;
    private String COMBAT_TAG_OTHER_NOT_IN_COMBAT;

    private String CANNOT_CRAFT_MESSAGE;

    private String JELLY_LEGS_ENABLED;
    private String JELLY_LEGS_DISABLED;

    private final FactionsExtras plugin;

    public MessageSettings(FactionsExtras plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        // Generic
        NO_PERMISSION = plugin.c().getMessage("NO_PERMISSION");
        CONSOLE_ONLY_COMMAND = plugin.c().getMessage("CONSOLE_ONLY_COMMAND");
        PLAYER_ONLY_COMMAND = plugin.c().getMessage("PLAYER_ONLY_COMMAND");
        PLAYER_NOT_FOUND = plugin.c().getMessage("PLAYER_NOT_FOUND");
        COMMAND_NOT_ENABLED = plugin.c().getMessage("COMMAND_NOT_ENABLED");

        // Command specific
        PING_SELF_MESSAGE = plugin.c().getMessage("PING_SELF_MESSAGE");
        PING_TARGET_MESSAGE = plugin.c().getMessage("PING_TARGET_MESSAGE");

        COMBAT_TAG_NOT_IN_COMBAT = plugin.c().getMessage("COMBAT_TAG_NOT_IN_COMBAT");
        COMBAT_TAG_SELF_MESSAGE = plugin.c().getMessage("COMBAT_TAG_SELF_MESSAGE");
        COMBAT_TAG_OTHER_MESSAGE = plugin.c().getMessage("COMBAT_TAG_OTHER_MESSAGE");
        COMBAT_TAG_OTHER_NOT_IN_COMBAT = plugin.c().getMessage("COMBAT_TAG_OTHER_NOT_IN_COMBAT");

        CANNOT_CRAFT_MESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("crafting.message"));

        JELLY_LEGS_ENABLED = plugin.c().getMessage("JELLY_LEGS_ENABLED");
        JELLY_LEGS_DISABLED = plugin.c().getMessage("JELLY_LEGS_DISABLED");
    }

    public String enabledJellyLegs() {
        return JELLY_LEGS_ENABLED;
    }

    public String disabledJellyLegs() {
        return JELLY_LEGS_DISABLED;
    }

    public String pingSelf(int ping) {
        return PING_SELF_MESSAGE.replace("%ping%", String.valueOf(ping));
    }

    public String pingTarget(Player target, int ping) {
        return PING_TARGET_MESSAGE.replace("%target%", target.getName()).replace("%ping%", String.valueOf(ping));
    }

    public String noPermissionMessage() {
        return NO_PERMISSION;
    }

    public String consoleOnlyCommand() {
        return CONSOLE_ONLY_COMMAND;
    }

    public String playerOnlyCommand() {
        return PLAYER_ONLY_COMMAND;
    }

    public String playerNotFound(String target) {
        return PLAYER_NOT_FOUND.replace("%target%", target);
    }

    public String notInCombat() {
        return COMBAT_TAG_NOT_IN_COMBAT;
    }

    public String tagSelf(Player player) {
        return COMBAT_TAG_SELF_MESSAGE.replace("%time%", String.valueOf(plugin.getManagerHandler().getTagManager().getRemainingTime(player)));
    }

    public String tagOther(Player player) {
        return COMBAT_TAG_OTHER_MESSAGE.replace("%time%", String.valueOf(plugin.getManagerHandler().getTagManager().getRemainingTime(player)).replace("%target%", player.getName()));
    }

    public String tagOtherNotInCombat(Player player) {
        return COMBAT_TAG_OTHER_NOT_IN_COMBAT.replace("%target%", player.getName());
    }

    public String cannotCraft(Material material) {
        return CANNOT_CRAFT_MESSAGE.replace("%item%", String.valueOf(material).toLowerCase());
    }

    public String commandNotEnabled(String command) {
        return COMMAND_NOT_ENABLED.replace("%command%", command);
    }
}
