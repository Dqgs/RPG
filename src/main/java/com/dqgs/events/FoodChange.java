package com.dqgs.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChange implements Listener {
    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }
}
