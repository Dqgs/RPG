package com.dqgs;

import com.dqgs.events.*;
import com.dqgs.files.PlayerData;
import com.dqgs.util.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class RPG extends JavaPlugin{

    public static RPG INSTANCE;

    public HashMap<UUID, User> playerStats = new HashMap<>();

    @Override
    public void onEnable() {
        events();
        commands();
        INSTANCE = this;

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
    }

    public void commands(){

    }
}
