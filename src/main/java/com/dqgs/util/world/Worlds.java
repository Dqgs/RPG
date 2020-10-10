package com.dqgs.util.world;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Worlds {

    public Worlds() {
    }

    public void loadWorlds() {
        Arrays.asList("1-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60-69",
                "70-79", "80-89", "90-99", "100").forEach(name -> world(name));
    }

    public void world(String name) {
        WorldCreator wc = new WorldCreator(name);
        wc.type(WorldType.NORMAL);
        wc.createWorld();
    }

    public Location worldSpawnLocation(String worldName){
        World world = Bukkit.getWorld(worldName);
        Location location = new Location(world, world.getSpawnLocation().getX(), world.getSpawnLocation().getY(), world.getSpawnLocation().getZ());
        return location;
    }
}
