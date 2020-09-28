package com.dqgs.events;

import com.dqgs.RPG;
import com.dqgs.util.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){
            event.setCancelled(true);
            Player player = (Player) event.getEntity();
            double damage = event.getDamage();
            User user = RPG.INSTANCE.playerStats.get(player.getUniqueId());
            user.getStats().setHealth(user, user.getStats().getHealth() - damage);
        }
    }
}
