package com.dqgs.events;

import com.dqgs.RPG;
import com.dqgs.files.PlayerData;
import com.dqgs.util.User.User;
import com.dqgs.util.items.ItemCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData.loadPlayer(player);
        User user = RPG.INSTANCE.playerStats.get(player.getUniqueId());
        user.getStats().rengen(user, 2);

        ItemCreator itemCreator = new ItemCreator();
        ItemStack itemStack = itemCreator.register(1);
        user.getPlayer().getInventory().addItem(itemStack);
    }
}
