package me.miniblacktw.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbyPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("setlobby").setExecutor(new CommandHandler(this));
        getCommand("lobby").setExecutor(new CommandHandler(this));
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("LobbyPlugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("LobbyPlugin disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (getConfig().contains("lobby.world")) {
            Location loc = new Location(
                    Bukkit.getWorld(getConfig().getString("lobby.world")),
                    getConfig().getDouble("lobby.x"),
                    getConfig().getDouble("lobby.y"),
                    getConfig().getDouble("lobby.z"),
                    (float) getConfig().getDouble("lobby.yaw"),
                    (float) getConfig().getDouble("lobby.pitch")
            );
            event.getPlayer().teleport(loc);
        }
    }
}
