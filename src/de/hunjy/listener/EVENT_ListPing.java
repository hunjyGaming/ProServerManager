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
import org.bukkit.event.server.ServerListPingEvent;

public class EVENT_ListPing implements Listener {

    @EventHandler
    public void on(ServerListPingEvent event) {
        event.setMaxPlayers((int)PSM.getInstance().getMainConfig().get("softCap"));
    }
}
