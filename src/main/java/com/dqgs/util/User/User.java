package com.dqgs.util.User;

import com.dqgs.util.User.scoreboards.ScoreBoardManager;
import org.bukkit.entity.Player;

public class User {

    private final Player player;
    private PlayerStats stats;
    private ScoreBoardManager scoreBoardManager;

    public User(Player player){
        this.player = player;
    }

    public PlayerStats getStats(){
        this.stats = PlayerStats.getFromUser(this);
        return this.stats;
    }

    public ScoreBoardManager getRandomStuff(){
        this.scoreBoardManager = ScoreBoardManager.getFromUser(this);
        return this.scoreBoardManager;
    }

    public Player getPlayer(){
        return player;
    }

}