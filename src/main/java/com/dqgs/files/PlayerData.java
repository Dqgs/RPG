package com.dqgs.files;

import com.dqgs.RPG;
import com.dqgs.util.User.User;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerData {
    private static File file;

    private static FileConfiguration datafile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("RPG").getDataFolder(), "PlayerData.yml");
        if (!file.exists())
            try {
                System.out.println("[RPG] Creating a new File");
                file.createNewFile();
                System.out.println("[RPG] File Has been Created");
            } catch (IOException iOException) {}
        datafile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return datafile;
    }

    public static void save() {
        try {
            datafile.save(file);
            System.out.println("[RPG] File Has been Saved");
        } catch (IOException e) {
            System.out.println("[RPG] Couldn't save playerdata.yml");
        }
    }

    public static void reload() {
        Bukkit.broadcastMessage("[RPG] Reloading");
        datafile = YamlConfiguration.loadConfiguration(file);
        System.out.println("[RPG] Reload Complete");
    }

    public static void loadPlayer(Player player) {
        if (!PlayerData.get().contains("player." + player.getUniqueId() + ".Defense") && !PlayerData.get().contains("player." + player.getUniqueId() + ".Health")) {

            PlayerData.get().set("player." + player.getUniqueId() + ".Defense", 0);
            PlayerData.get().set("player." + player.getUniqueId() + ".Health", 100d);
            PlayerData.get().set("player." + player.getUniqueId() + ".MaxHealth", 100d);
            PlayerData.get().set("player." + player.getUniqueId() + ".Mana", 100d);
            PlayerData.get().set("player." + player.getUniqueId() + ".MaxMana", 100d);
            PlayerData.save();
            
            int Defense = PlayerData.get().getInt("player." + player.getUniqueId() + ".Defense");
            double Health = PlayerData.get().getInt("player." + player.getUniqueId() + ".Health");
            double MaxHealth = PlayerData.get().getInt("player." + player.getUniqueId() + ".MaxHealth");
            int Mana = PlayerData.get().getInt("player." + player.getUniqueId() + ".Mana");
            int MaxMana = PlayerData.get().getInt("player." + player.getUniqueId() + ".MaxMana");
            double XP = PlayerData.get().getDouble("player." + player.getUniqueId() + ".XP");

            RPG.INSTANCE.playerStats.put(player.getUniqueId(), new User(player));
            User user = RPG.INSTANCE.playerStats.get(player.getUniqueId());
            user.getRandomStuff().setBoard(user);
            user.getStats().setDefense(user, Defense);
            user.getStats().setHealth(user, Health);
            user.getStats().setMaxHealth(user, MaxHealth);
            user.getStats().setMana(user, Mana);
            user.getStats().setMana(user, MaxMana);
            user.getStats().setXp(user, XP);

        } else {

            int Defense = PlayerData.get().getInt("player." + player.getUniqueId() + ".Defense");
            double Health = PlayerData.get().getInt("player." + player.getUniqueId() + ".Health");
            double MaxHealth = PlayerData.get().getInt("player." + player.getUniqueId() + ".MaxHealth");
            int Mana = PlayerData.get().getInt("player." + player.getUniqueId() + ".Mana");
            int MaxMana = PlayerData.get().getInt("player." + player.getUniqueId() + ".MaxMana");
            double XP = PlayerData.get().getDouble("player." + player.getUniqueId() + ".XP");

            RPG.INSTANCE.playerStats.put(player.getUniqueId(), new User(player));
            User user = RPG.INSTANCE.playerStats.get(player.getUniqueId());
            user.getRandomStuff().setBoard(user);
            user.getStats().setDefense(user, Defense);
            user.getStats().setHealth(user, Health);
            user.getStats().setMaxHealth(user, MaxHealth);
            user.getStats().setMana(user, Mana);
            user.getStats().setMana(user, MaxMana);
            user.getStats().setXp(user, XP);
        }
    }

    public static void savePlayer(Player player) {
        User user = RPG.INSTANCE.playerStats.get(player.getUniqueId());
        if (RPG.INSTANCE.playerStats.containsKey(player.getUniqueId())) {
            user.getStats().transferLevelToXp(user);

            PlayerData.get().set("player." + player.getUniqueId() + ".Defense", user.getStats().getDefense());
            PlayerData.get().set("player." + player.getUniqueId() + ".Health", user.getStats().getHealth());
            PlayerData.get().set("player." + player.getUniqueId() + ".MaxHealth", user.getStats().getMaxHealth());
            PlayerData.get().set("player." + player.getUniqueId() + ".Mana", user.getStats().getMaxMana());
            PlayerData.get().set("player." + player.getUniqueId() + ".MaxMana", user.getStats().getMaxMana());
            PlayerData.get().set("player." + player.getUniqueId() + ".XP", user.getStats().getXp());
            PlayerData.save();

            RPG.INSTANCE.playerStats.remove(player.getUniqueId());
        }
    }
}