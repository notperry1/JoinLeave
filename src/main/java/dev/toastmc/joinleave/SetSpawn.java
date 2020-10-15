package dev.toastmc.joinleave;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

    private final Main plugin;

    public SetSpawn(Main pl) {
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("joinleave.setspawn")) {
            plugin.getConfig().set("spawn.world", p.getLocation().getWorld().getName());
            plugin.getConfig().set("spawn.x", p.getLocation().getX());
            plugin.getConfig().set("spawn.y", p.getLocation().getY());
            plugin.getConfig().set("spawn.z", p.getLocation().getZ());
            plugin.getConfig().set("spawn.yaw", p.getLocation().getYaw());
            plugin.saveConfig();
            p.sendMessage(ChatColor.GREEN + "World spawn set to " + plugin.getSpawnLoc());
        } else {
            p.sendMessage(ChatColor.RED + "You do not have the permission.");
            return false;
        }
        return true;
    }
}
