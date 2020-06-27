package me.doges.fextras.command.commands;

import me.doges.fextras.command.CommandExecutor;
import me.doges.fextras.module.jellylegs.JellyLegs;
import me.doges.fextras.settings.impl.MessageSettings;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JellylegsCommand extends CommandExecutor {

    private MessageSettings settings;

    public JellylegsCommand() {
        setCommand("jellylegs");
        setPermission("fextras.commands.jellylegs");
        setUsage("/jellylegs <player>");
        setPlayer();
        setLength(1);
        setFlexibleArgs();

        this.settings = plugin.getSettings().getMessages();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // Check if the module is enabled
        if (plugin.getManagerHandler().getModuleManager().isEnabled("jelly-legs")) {
            JellyLegs main = (JellyLegs) plugin.getManagerHandler().getModuleManager().getModuleFromName("jelly-legs");

            if (main.getUsers().contains(player.getUniqueId())) {
                main.getUsers().remove(player.getUniqueId());
                player.sendMessage(settings.disabledJellyLegs());
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
            } else {
                main.getUsers().add(player.getUniqueId());
                player.sendMessage(settings.enabledJellyLegs());
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
            }
        } else {
            player.sendMessage(settings.commandNotEnabled(this.getCommand()));
        }
    }
}
