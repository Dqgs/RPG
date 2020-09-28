package com.dqgs.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class Regen implements Listener {
    @EventHandler
    public void onRegen(EntityRegainHealthEvent event){
        if (event.getEntity() instanceof Player){
            event.setCancelled(true);
        }
    }
}
