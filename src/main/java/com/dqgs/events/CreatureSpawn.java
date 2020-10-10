package com.dqgs.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawn implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event){
        String world = event.getLocation().getWorld().getName();
        if (world.equalsIgnoreCase("1-9")){

        }
        if (world.equalsIgnoreCase("world")){
            //event.setCancelled(true);
        }
    }
}