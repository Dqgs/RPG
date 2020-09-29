package com.dqgs.events;

import com.dqgs.RPG;
import com.dqgs.files.PlayerData;
import com.dqgs.util.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        User user = RPG.INSTANCE.playerStats.get(player.getUniqueId());
        user.getStats().transferLevelToXp(user);
        PlayerData.savePlayer(player);
    }
}
