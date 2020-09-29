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
        this.stats = PlayerStats.getFromUser(this);
        return this.stats;
    }

    public RandomStuff getRandomStuff(){
        this.randomStuff = RandomStuff.getFromUser(this);
        return this.randomStuff;
    }

    public Player getPlayer(){
        return player;
    }

    public void pickRole() {
        if (getStats().getRoles() != null) {
            switch (getStats().getRoles()) {
                case UNPICKED:
                    getStats().setRoles(this, Roles.MAGE);
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