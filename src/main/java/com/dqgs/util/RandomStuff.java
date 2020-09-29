package com.dqgs.util;

import com.dqgs.RPG;
import com.dqgs.util.classes.Mage;
import com.dqgs.util.classes.Roles;
import com.dqgs.util.scoreboards.ScoreHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class RandomStuff {

    private Player player;
    private static Map<User, RandomStuff> getuser = new HashMap<>();

    public RandomStuff(Player player){
        this.player = player;
    }

    public static RandomStuff getFromUser(User user) {
        RandomStuff stats = getuser.get(user);
        if (stats == null) {
            stats = new RandomStuff(user.getPlayer());
            getuser.put(user, stats);
        }
        return stats;
    }

    public void setBoard(User user){
        ScoreHelper scoreboard = ScoreHelper.createScore(this.player);
        scoreboard.setTitle("" + ChatColor.AQUA + ChatColor.BOLD +"RPG");

        scoreboard.setSlot(6, ChatColor.RED + "Health: ");
        scoreboard.setSlot(5, user.getStats().getHealth() + " / " + user.getStats().getMaxHealth());
        scoreboard.setSlot(4, ChatColor.GREEN + "Defense: ");
        scoreboard.setSlot(3, String.valueOf(user.getStats().getDefense()));
        scoreboard.setSlot(2, ChatColor.AQUA + "Mana: ");
        scoreboard.setSlot(1, user.getStats().getMana() + " / " + user.getStats().getMaxMana());
    }

    public void updateScoreBoard(User user){
        if (player != null) {
            Bukkit.broadcastMessage("Player");
            if (ScoreHelper.hasScore(this.player)) {
                Bukkit.broadcastMessage("Scorehelper");
                ScoreHelper scoreHelper = ScoreHelper.getByPlayer(player);
                Bukkit.broadcastMessage("get player");
                scoreHelper.setSlot(5, user.getStats().getHealth() + " / " + user.getStats().getMaxHealth());
                Bukkit.broadcastMessage("get stats health");
                scoreHelper.setSlot(3, String.valueOf(user.getStats().getDefense()));
                scoreHelper.setSlot(1, user.getStats().getMana() + " / " + user.getStats().getMaxMana());
            }
        }
    }
}
