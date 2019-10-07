package de.hunjy.utils.random;
/*
    Create by RiedCrafter on 07.10.2019
    @author: RiedCrafter
    @date: 07.10.2019
    @time: 16:49
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class RandomUtil {

    private int count;

    public RandomUtil(int count) {
        this.count = count;
    }

    public Player getRandomPlayer() {
        int number = new Random().nextInt(Bukkit.getOnlinePlayers().size());
        Player randomPlayer = (Player) PSM.getInstance().getServer().getOnlinePlayers().toArray()[number];
        return randomPlayer;
    }

    public int getRandomInt() {
        int randomInt = new Random().nextInt(count);
        return randomInt;
    }

    public String getRandomString() {
        StringBuilder randomString = new StringBuilder();
        while(this.count-- != 0) {
            String keys = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
            int character = (int) (Math.random() * keys.length());
            randomString.append(keys.charAt(character));
        }
        return randomString.toString();
    }

}
