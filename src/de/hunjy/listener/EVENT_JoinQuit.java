package de.hunjy.listener;

import de.hunjy.PSM;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/*
    Create by hunjy on 29.09.2019
    @auther: hunjy
    @date: 29.09.2019
    @time: 20:25
    @projekt: ProServerManager
*/
public class EVENT_JoinQuit implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if( ! (boolean) PSM.getInstance().getMainConfig().get("enableJoinMessage")) {
            event.setJoinMessage(null);
        }else {
            String msg = (String) PSM.getInstance().getMainConfig().get("JoinMessage");
            msg = msg.replaceAll("&", "§").replaceAll("%player%", player.getDisplayName());
            event.setJoinMessage(msg);
        }
        if (player.hasPermission((String) PSM.getInstance().getMainConfig().get("joinePermission"))) {
            player.sendMessage(PSM.getInstance().getMessageManager().get("test").toString());
        }
    }



}
