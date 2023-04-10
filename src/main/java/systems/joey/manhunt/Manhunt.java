package systems.joey.manhunt;

import org.bukkit.plugin.java.JavaPlugin;
import systems.joey.manhunt.commands.ManhuntCommand;
import systems.joey.manhunt.listeners.CompassListeners;
import systems.joey.manhunt.listeners.RespawnListeners;

public final class Manhunt extends JavaPlugin {

    @Override
    public void onEnable() {
        // register commands and events
        getCommand("manhunt").setExecutor(new ManhuntCommand(this));
        getServer().getPluginManager().registerEvents(new CompassListeners(), this);
        getServer().getPluginManager().registerEvents(new RespawnListeners(), this);

        getLogger().info("Manhunt Enabled");
    }

    private static String trackedPlayer = null;

    public static String getTracked() {
        return trackedPlayer;
    }

    public void setRunner(String runner) {
        trackedPlayer = runner;
    }
}
