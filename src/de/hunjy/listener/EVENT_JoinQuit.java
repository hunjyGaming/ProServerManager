package de.hunjy.listener;

import de.hunjy.PSM;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

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

    @EventHandler
    public void on(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if((boolean)PSM.getInstance().getMainConfig().get("maintenanceMode") == true) {
            if(!player.hasPermission("psm.admin")) {
                event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                event.setKickMessage("§7§m-----x---------------x-----\n\n§cZur Zeit werden §4Wartungen\n§cdurchgeführt. Bitte habe Geduld.\n\n§7§m-----x---------------x-----");
            }
        }
    }



}
