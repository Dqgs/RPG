package com.dqgs.util;

import com.dqgs.util.classes.Mage;
import com.dqgs.util.classes.Roles;
import org.bukkit.entity.Player;

public class User {

    private final Player player;
    private PlayerStats stats;
    private RandomStuff randomStuff;

    public User(Player player){
        this.player = player;
    }

    public PlayerStats getStats(){
        return this.stats;
    }

    public RandomStuff getRandomStuff(){
        return this.randomStuff;
    }

    public Player getPlayer(){
        return player;
    }

    public void pickRole() {
        if (getStats().getRoles() != null) {
            switch (getStats().getRoles()) {
                case UNPICKED:
                    player.sendMessage("You are unpicked");
                    getStats().setRoles(this, Roles.MAGE);
                    player.sendMessage("You are now a " + getStats().getRoles());
                    break;
                case MAGE:
                    Mage mage = new Mage();
                    break;
                case TEST:
                    break;
                case TEST2:
                    break;
            }
        }
    }
}