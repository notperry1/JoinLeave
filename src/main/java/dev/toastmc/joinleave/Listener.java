package dev.toastmc.joinleave;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent e){
        if(!e.getPlayer().hasPlayedBefore()){
            for(Player online : Bukkit.getOnlinePlayers()){
                online.sendMessage(ChatColor.LIGHT_PURPLE + e.getPlayer().getName() + " has joined for the first time.");
            }
        }
        e.setJoinMessage(ChatColor.GRAY + e.getPlayer().getName() + " has joined");
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(ChatColor.GRAY + e.getPlayer().getName() + " has left");
    }
}
