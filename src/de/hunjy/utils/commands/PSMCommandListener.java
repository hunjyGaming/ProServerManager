package de.hunjy.utils.commands;
/*
    Create by RiedCrafter on 30.09.2019
    @author: RiedCrafter
    @date: 30.09.2019
    @time: 13:14
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PSMCommandListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (message.startsWith("#")) {
            if(player.hasPermission("psm.admin")) {
                event.setCancelled(true);
                if(PSMCommandHandler.executeCommand(event.getPlayer(), message)) {

                } else {
                    event.getPlayer().sendMessage(PSM.Prefix + "§cDieser Command existiert nicht!");
                }
            } else {
                event.getPlayer().sendMessage(PSM.Prefix + " §cDazu hast du keine Rechte!");
                event.setCancelled(true);
            }
        }
    }


}
