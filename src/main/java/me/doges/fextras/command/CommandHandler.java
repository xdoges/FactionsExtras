package me.doges.fextras.command;

import me.doges.fextras.FactionsExtras;
import me.doges.fextras.command.commands.JellylegsCommand;
import me.doges.fextras.command.commands.PingCommand;
import me.doges.fextras.command.commands.TagCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandHandler implements org.bukkit.command.CommandExecutor {

    private final FactionsExtras plugin;
    private HashMap<String, CommandExecutor> commands = new HashMap<>();

    public CommandHandler(FactionsExtras plugin) {
        this.plugin = plugin;

        commands.put("ping", new PingCommand());
        commands.put("tag", new TagCommand());
        commands.put("jellylegs", new JellylegsCommand());

        registerCommands();
    }

    public void registerCommands() {
        for (String cmd : commands.keySet()) {
            plugin.getCommand(cmd).setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String name = command.getName();
        if (commands.containsKey(name)) {
            final CommandExecutor cmd = commands.get(name);

            if (cmd.getPermission() != null && !sender.hasPermission(cmd.getPermission())) {
                sender.sendMessage(plugin.getSettings().getMessages().noPermissionMessage());
                return true;
            }

            if (!cmd.isBoth()) {
                if (cmd.isConsole() && sender instanceof Player) {
                    sender.sendMessage(plugin.getSettings().getMessages().consoleOnlyCommand());
                    return true;
                }

                if (cmd.isPlayer() && sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(plugin.getSettings().getMessages().playerOnlyCommand());
                    return true;
                }
            }

            if (cmd.getLength() > args.length && !cmd.isFlexibleArgs()) {
                sender.sendMessage(ChatColor.RED + "Usage: " + cmd.getUsage());
                return true;
            }

            cmd.execute(sender, args);
            return true;
        }
        return false;
    }
}
