package com.dqgs;

import com.dqgs.events.*;
import com.dqgs.files.PlayerData;
import com.dqgs.util.User.User;
import com.dqgs.util.world.Worlds;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class RPG extends JavaPlugin{

    public static RPG INSTANCE;

    public HashMap<UUID, User> playerStats = new HashMap<>();
    public HashMap<Integer, ItemStack> items = new HashMap<>();

    @Override
    public void onEnable() {
        long now = System.currentTimeMillis();
        events();
        commands();
        INSTANCE = this;
        Worlds worlds = new Worlds();
        worlds.loadWorlds();


        //Files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        PlayerData.setup();
        PlayerData.get().addDefault("player", "UUID");
        PlayerData.get().options().copyDefaults(true);
        PlayerData.save();
        for (Player player : Bukkit.getOnlinePlayers()){
            PlayerData.loadPlayer(player);
        }
        long end = System.currentTimeMillis();
        long seconds = (end - now) / 1000;
        System.out.println("[RPG] It took " + seconds + "s to start");
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()){
            PlayerData.savePlayer(player);
        }
    }

    public void events(){
        PluginManager server = Bukkit.getServer().getPluginManager();
        server.registerEvents(new Damage(), this);
        server.registerEvents(new FoodChange(), this);
        server.registerEvents(new Join(), this);
        server.registerEvents(new Leave(), this);
        server.registerEvents(new Regen(), this);
        server.registerEvents(new CreatureSpawn(), this);
    }

    public void commands(){

    }
}
