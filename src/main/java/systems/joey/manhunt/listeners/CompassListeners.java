package systems.joey.manhunt.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.CompassMeta;
import systems.joey.manhunt.Manhunt;

import static org.bukkit.Bukkit.getPlayer;

public class CompassListeners implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null && event.getItem().getType() == Material.COMPASS) {
                String playerTrack = Manhunt.getTracked();
                if (playerTrack == null && playerTrack.equals(event.getPlayer().getName())) return;

                Player player = event.getPlayer();
                Player target = getPlayer(Manhunt.getTracked());

                if (target != null && target.getWorld().getName().equals(player.getWorld().getName()) && !target.getName().equals(player.getName())) {
                    World world = player.getWorld();
                    CompassMeta meta = (CompassMeta) event.getItem().getItemMeta();
                    switch (player.getWorld().getEnvironment()) {
                        case NORMAL -> {
                            meta.setLodestone(null);
                            meta.setLodestoneTracked(false);
                            event.getItem().setItemMeta(meta);
                            player.setCompassTarget(target.getLocation());
                        }
                        case NETHER, THE_END -> {
                            meta.setLodestone(target.getLocation());
                            meta.setLodestoneTracked(false);
                            meta.setDisplayName(ChatColor.RESET + "Compass");
                            event.getItem().setItemMeta(meta);
                        }
                    }
                    //player.sendMessage(ChatColor.GREEN + "Compass is now pointing to " + target.getName() + ".");
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "Compass is now pointing to " + target.getName() + "."));
                } else {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "There are no players to track."));
                }
            }
        }
    }
}
