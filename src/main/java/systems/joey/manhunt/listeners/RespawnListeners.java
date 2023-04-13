package systems.joey.manhunt.listeners;

import it.unimi.dsi.fastutil.chars.CharAVLTreeSet;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import systems.joey.manhunt.Manhunt;

public class RespawnListeners implements Listener {
    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        givePlayerCompass(player, null);
    }
    public static void givePlayerCompass(Player player, Player target) {
        if (player.getName().equals(Manhunt.getTracked())) return;
        if (Manhunt.getTracked() == null) return;
        if (target != null) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.BOLD + "" + ChatColor.GREEN + "Manhunt begins. You have been given a compass tracking " + target.getName()));
        }
        player.getInventory().addItem(new ItemStack(Material.COMPASS));
    }
}
