package systems.joey.manhunt.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import systems.joey.manhunt.Manhunt;
import systems.joey.manhunt.listeners.RespawnListeners;
public class ManhuntCommand implements CommandExecutor {
    private final Manhunt plugin;

    public ManhuntCommand(Manhunt plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 0) {
            sender.sendMessage(ChatColor.RED + "Please specify a player to target to be able to start the manhunt");
            return true;
        }
        if (sender instanceof Player player) {
            Player target = plugin.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Player not found");
                return true;
            }
            plugin.setRunner(args[0]);
            player.sendMessage(ChatColor.GREEN + "Manhunt Started with target " + args[0]);
            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                if (onlinePlayer != target) {
                    RespawnListeners.givePlayerCompass(onlinePlayer, target);
                }
            }
        }
        return true;
    }
}
