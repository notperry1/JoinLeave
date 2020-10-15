package dev.toastmc.joinleave;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Listener implements org.bukkit.event.Listener {

    private final Main plugin;

    public Listener(Main pl) {
        plugin = pl;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore()){
            for(Player online : Bukkit.getOnlinePlayers()){
                online.sendMessage(ChatColor.LIGHT_PURPLE + p.getName() + " has joined for the first time.");
            }
        }
        e.setJoinMessage(ChatColor.GRAY + p.getName() + " has joined");
        plugin.spawnTeleport(p);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onRespawn(PlayerRespawnEvent e){
        if(plugin.spawn != null){
            e.setRespawnLocation(plugin.spawn);
            plugin.spawnTeleport(e.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(ChatColor.GRAY + e.getPlayer().getName() + " has left");
    }
}
