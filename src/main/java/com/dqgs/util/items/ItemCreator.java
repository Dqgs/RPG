package com.dqgs.util.items;

import com.dqgs.RPG;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemCreator {

    public ItemStack item(Material material, String name, int id,int strength, int defense, double maxHealth, int maxMana){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> lore = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (strength != 0 &&
            !lore.contains("Strength +" +strength)){
                lore.add(i, "Strength +" +strength);
            }
            if (defense != 0 &&
                    !lore.contains("defense +" +defense)){
                lore.add(i, "defense +" +defense);
            }
            if (maxHealth != 0 &&
                    !lore.contains("maxHealth +" +maxHealth)){
                lore.add(i, "maxHealth +" +maxHealth);
            }
            if (maxMana != 0 &&
                    !lore.contains("maxMana +" +maxMana)){
                lore.add(i, "maxMana +" +maxMana);
            }
        }
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        ItemStack item = NBT(itemStack, strength, defense, maxHealth, maxMana);
        RPG.INSTANCE.items.put(id, item);
        return item;
    }

    public ItemStack register(int id){
        item(Material.DIAMOND_SWORD, "Test", 1, 1, 1,1,1);
        return RPG.INSTANCE.items.get(id);
    }

    public ItemStack NBT(ItemStack itemStack, int strength, int defense, double maxHealth, int maxMana){
        net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = nmsStack.getTag() != null ? nmsStack.getTag() : new NBTTagCompound();

        NBTTagList atrModifiers = new NBTTagList();

        NBTTagCompound tags = new NBTTagCompound();
        if (defense != 0) {
            tag.setInt("defense", defense);
        }
        if (strength != 0) {
            tag.setDouble("strength", strength);
        }
        if (maxHealth != 0){
            tag.setDouble("maxHealth", maxHealth);
        }
        if (maxHealth != 0){
            tag.setInt("maxMana", maxMana);
        }
        NBTTagCompound hide = new NBTTagCompound();

        hide.setInt("HideFlags", 16);
        
        atrModifiers.add(tags);
        atrModifiers.add(hide);

        nmsStack.setTag(tag);

        return CraftItemStack.asBukkitCopy(nmsStack);
    }
}
