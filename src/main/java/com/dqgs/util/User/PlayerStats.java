package com.dqgs.util.User;

import com.dqgs.RPG;
import com.dqgs.files.PlayerData;
import com.dqgs.util.Math;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class PlayerStats {
    Player player;
    public int defense, mana, maxMana, level;
    public double health, maxHealth, xp;
    private static Map<User, PlayerStats> getuser = new HashMap<>();

    public PlayerStats(int defense, double health, double maxHealth, int mana, int maxMana, double xp, Player player){
        this.defense = defense;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        this.xp = xp;
        this.player = player;
    }



    public static PlayerStats getFromUser(User user) {
        PlayerStats stats = getuser.get(user);
        if (stats == null) {
            int Defense = PlayerData.get().getInt("player." + user.getPlayer().getUniqueId() + ".Defense");
            double Health = PlayerData.get().getInt("player." + user.getPlayer().getUniqueId() + ".Health");
            double MaxHealth = PlayerData.get().getInt("player." + user.getPlayer().getUniqueId() + ".MaxHealth");
            int Mana = PlayerData.get().getInt("player." + user.getPlayer().getUniqueId() + ".Mana");
            int MaxMana = PlayerData.get().getInt("player." + user.getPlayer().getUniqueId() + ".MaxMana");
            int xp = PlayerData.get().getInt("player." + user.getPlayer().getUniqueId() + ".XP");

            stats = new PlayerStats(Defense, Health,MaxHealth,Mana,MaxMana, xp, user.getPlayer());
            getuser.put(user, stats);
        }
        return stats;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(User user, int defense) {
        this.defense = defense;
        user.getRandomStuff().updateScoreBoard(user);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(User user, int mana) {
        this.mana = mana;
        user.getRandomStuff().updateScoreBoard(user);
    }
    public int getMaxMana(){
        return maxMana;
    }

    public void setMaxMana(User user, int maxMana){
        this.maxMana = maxMana;
        user.getRandomStuff().updateScoreBoard(user);
    }

    public double getHealth() {
        return this.health;
    }

    public void setHealth(User user, double health) {
        this.health = Math.Round(health);
        updateHealth(user);
    }

    public void updateHealth(User user){
        if (player != null) {
            if (getHealth() <= 0) {
                player.setHealth(0);
                setHealth(user, getMaxHealth());
                user.getRandomStuff().updateScoreBoard(user);
            } else {
                player.setHealth((getHealth() / getMaxHealth()) * 20);
                user.getRandomStuff().updateScoreBoard(user);

            }
        }
    }

    public double getMaxHealth(){
        return maxHealth;
    }

    public void setMaxHealth(User user, double maxHealth){
        this.maxHealth = maxHealth;
        user.getRandomStuff().updateScoreBoard(user);
    }

    public void transferXpToLevel(User user){
        double xpNeeded;
        xpNeeded = 100;
        while (getXp() >= xpNeeded){
            setXp(user, getXp() - xpNeeded);
            setLevel(user, getLevel() + 1);
        }
    }

    public void transferLevelToXp(User user) {
        while (getLevel() >= 1) {
            setXp(user, getXp() + 100);
            setLevel(user, getLevel() - 1);
        }
    }

    public void setXp(User user, double xp){
        this.xp = xp;
        user.getRandomStuff().updateScoreBoard(user);
    }

    public void setLevel(User user, int level){
        this.level = level;
        user.getRandomStuff().updateScoreBoard(user);
    }

    public double getXp(){
        return xp;
    }
    public int getLevel(){
        return level;
    }

    public void rengen(User user, int seconds){
        new BukkitRunnable(){
            @Override
            public void run() {
                    if (getMaxHealth() >= (0.8 + getMaxHealth() * 0.005) + getHealth()) {
                        setHealth(user, (0.8 + getMaxHealth() * 0.005) + getHealth());
                    } else if (getMaxHealth() >= getHealth()) {
                        setHealth(user, getMaxHealth());
                    }
                }
        }.runTaskTimer(RPG.INSTANCE, 20 * seconds, 20 * seconds );
    }
}