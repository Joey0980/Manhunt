package systems.joey.manhunt.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.joey.manhunt.Manhunt;

public class ManhuntCommand implements CommandExecutor {
    private final Manhunt plugin;
    public ManhuntCommand(Manhunt plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Please specify a player to start the manhunt");
        }
        if (sender instanceof Player player) {
            plugin.setRunner(args[0]);
            player.sendMessage(ChatColor.GREEN + "Manhunt Started with target " + args[0]);
        }
        return true;
    }
}