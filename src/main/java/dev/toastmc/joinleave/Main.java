package dev.toastmc.joinleave;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;

public final class Main extends JavaPlugin {

    public boolean spawnFeature = true;
    public Location spawn = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginCommand command = getCommand("setspawn");
        PluginCommand command1 = getCommand("spawn");
        if (command != null) {
            command.setExecutor(new SetSpawn(this));
            System.out.println("Set spawn command loaded");
        }
        if (command1 != null) {
            command1.setExecutor(new Spawn(this));
            System.out.println("Spawn command loaded");
        }
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new Listener(this), this);
//        spawnFeature = getConfig().getBoolean("spawn.enabled");
        System.out.println("JoinLeave finished loading.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void spawnTeleport(Player p){
        if(spawnFeature){
            try {
                World w = Bukkit.getServer().getWorld(getConfig().getString("spawn.world"));
                double x = getConfig().getDouble("spawn.x");
                double y = getConfig().getDouble("spawn.y");
                double z = getConfig().getDouble("spawn.z");
                float yaw = getConfig().getInt("spawn.yaw");
                spawn = new Location(w, x, y, z, yaw, 0);
                p.teleport(spawn);
            } catch (Exception exception) {}
        }
    }

    public String getSpawnLoc(){
        final DecimalFormat format = new DecimalFormat("#.#");
        final String x = format.format(getConfig().getDouble("spawn.x"));
        final String y = format.format(getConfig().getDouble("spawn.y"));
        final String z = format.format(getConfig().getDouble("spawn.z"));
        return x + ", " + y + ", " + z;
    }
}
