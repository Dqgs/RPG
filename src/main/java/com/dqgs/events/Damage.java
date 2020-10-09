package com.dqgs.events;

import com.dqgs.RPG;
import com.dqgs.util.User.User;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class Damage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            double damage = event.getDamage();
            User user = RPG.INSTANCE.playerStats.get(player.getUniqueId());
            user.getStats().setHealth(user, user.getStats().getHealth() - damage);
            if (user.getPlayer().getInventory().getItemInMainHand() != null){
                ItemStack item = player.getInventory().getItemInMainHand();
                net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                if (!nmsItem.hasTag()) Bukkit.broadcastMessage("NO TAGS");
                NBTTagCompound tag = nmsItem.getTag();
                if (tag.getInt("Defense") > 2){
                    Bukkit.broadcastMessage("TRUESSSS");
                } else {
                    Bukkit.broadcastMessage("NBT: " );
                }
            } else {
                Bukkit.broadcastMessage("NULL ITEm");
            }
        }
    }
}
