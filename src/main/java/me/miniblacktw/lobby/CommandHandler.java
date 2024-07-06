package me.miniblacktw.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandHandler implements CommandExecutor {

    private JavaPlugin plugin;

    public CommandHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("setlobby")) {
                Location loc = player.getLocation();
                plugin.getConfig().set("lobby.world", loc.getWorld().getName());
                plugin.getConfig().set("lobby.x", loc.getX());
                plugin.getConfig().set("lobby.y", loc.getY());
                plugin.getConfig().set("lobby.z", loc.getZ());
                plugin.getConfig().set("lobby.yaw", loc.getYaw());
                plugin.getConfig().set("lobby.pitch", loc.getPitch());
                plugin.saveConfig();
                player.sendMessage("§bLobby location set!");
            } else if (cmd.getName().equalsIgnoreCase("lobby")) {
                if (plugin.getConfig().contains("lobby.world")) {
                    Location loc = new Location(
                            Bukkit.getWorld(plugin.getConfig().getString("lobby.world")),
                            plugin.getConfig().getDouble("lobby.x"),
                            plugin.getConfig().getDouble("lobby.y"),
                            plugin.getConfig().getDouble("lobby.z"),
                            (float) plugin.getConfig().getDouble("lobby.yaw"),
                            (float) plugin.getConfig().getDouble("lobby.pitch")
                    );
                    player.teleport(loc);
                    String lobbyServer = plugin.getConfig().getString("lobby.server");
                    if (lobbyServer != null && !lobbyServer.isEmpty()) {
                        player.performCommand("server " + lobbyServer);
                    }
                } else {
                    player.sendMessage("§cYou must setup a lobby with §b/setlobby §cfirst");
                }
            } else if (cmd.getName().equalsIgnoreCase("lobbyplugin")) {
                player.sendMessage("§bLobbyPlugin v2 &eby miniblack_TW");
            }
        }
        return true;
    }
}