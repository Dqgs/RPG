package com.dqgs.events;

import com.dqgs.RPG;
import com.dqgs.files.PlayerData;
import com.dqgs.util.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        User user = RPG.INSTANCE.playerStats.get(player.getUniqueId());
        PlayerData.loadPlayer(user, player);
        user.getRandomStuff().setBoard(user);
        user.pickRole();
        user.getStats().rengen(user, 2);
        Bukkit.broadcastMessage("Role: " + user.getStats().getRoles());
    }
}
