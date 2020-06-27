package me.doges.fextras.command.commands;

import me.doges.fextras.command.CommandExecutor;
import me.doges.fextras.module.combattag.managers.TagManager;
import me.doges.fextras.settings.impl.MessageSettings;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagCommand extends CommandExecutor {

    private MessageSettings messages;
    private TagManager tagManager;

    public TagCommand() {
        setCommand("tag");
        setPermission("fextras.commands.tag");
        setUsage("/tag [player]");
        setPlayer();
        setLength(1);
        setFlexibleArgs();

        this.messages = plugin.getSettings().getMessages();
        this.tagManager = plugin.getManagerHandler().getTagManager();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null || !target.isOnline()) {
                player.sendMessage(messages.playerNotFound(args[0]));
                return;
            }

            if (!tagManager.isTagged(target)) {
                player.sendMessage(messages.tagOtherNotInCombat(target));
                return;
            } else {
                player.sendMessage(messages.tagOther(target));
            }
        } else if (args.length == 0) {
            if (!tagManager.isTagged(player)) {
                player.sendMessage(messages.notInCombat());
                return;
            } else {
                player.sendMessage(messages.tagSelf(player));
            }
        }
    }
}
