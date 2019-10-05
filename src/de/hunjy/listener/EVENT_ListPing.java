package de.hunjy.listener;
/*
    Create by RiedCrafter on 04.10.2019
    @author: RiedCrafter
    @date: 04.10.2019
    @time: 18:29
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class EVENT_ListPing implements Listener {

    @EventHandler
    public void on(ServerListPingEvent event) {
        event.setMaxPlayers((int)PSM.getInstance().getMainConfig().get("softCap"));
        if((boolean)PSM.getInstance().getMainConfig().get("maintenanceMode") == true) {
            event.setMotd(((String) PSM.getInstance().getMainConfig().get("maintenanceMotd1")).replaceAll("&", "§") + "\n" + ((String) PSM.getInstance().getMainConfig().get("maintenanceMotd2")).replaceAll("&", "§"));
        } else {
            event.setMotd(((String) PSM.getInstance().getMainConfig().get("motd1")).replaceAll("&", "§") + "\n" + ((String) PSM.getInstance().getMainConfig().get("motd2")).replaceAll("&", "§"));
        }
    }

}
