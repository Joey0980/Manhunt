package systems.joey.manhunt.listeners;

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
        if (Manhunt.getTracked() == null) return;
        if (!player.getName().equals(Manhunt.getTracked())) player.getInventory().addItem(new ItemStack(Material.COMPASS));
    }
}
