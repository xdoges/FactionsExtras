package me.doges.fextras.command.commands;

import me.doges.fextras.command.CommandExecutor;
import me.doges.fextras.nms.ReflectionUtil;
import me.doges.fextras.settings.impl.MessageSettings;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand extends CommandExecutor {

    private MessageSettings messages;

    public PingCommand() {
        setCommand("ping");
        setPermission("fextras.commands.ping");
        setUsage("/ping [player]");
        setPlayer();
        setLength(1);
        setFlexibleArgs();

        this.messages = plugin.getSettings().getMessages();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length == 1) {
            Player target = plugin.getServer().getPlayer(args[0]);

            if (target == null || !target.isOnline()) {
                player.sendMessage(messages.playerNotFound(args[0]));
                return;
            }

            int ping = ReflectionUtil.getPlayerPing(target);

            player.sendMessage(messages.pingTarget(target, ping));
        } else if (args.length == 0) {

            int ping = ReflectionUtil.getPlayerPing(player);

            player.sendMessage(messages.pingSelf(ping));
        }
    }
}
