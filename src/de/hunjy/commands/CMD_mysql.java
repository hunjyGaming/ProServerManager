package de.hunjy.commands;
/*
    Create by RiedCrafter on 03.10.2019
    @author: RiedCrafter
    @date: 03.10.2019
    @time: 17:33
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import de.hunjy.utils.commands.PSMCommand;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class CMD_mysql implements PSMCommand {
    @Override
    public String getName() {
        return "mysql";
    }

    @Override
    public String getDescription() {
        return "Zeigt informationen über die Datenbank";
    }

    @Override
    public void execute(Player player, String[] args) {
        if(player.hasPermission("psm.admin")) {
            if(args.length == 1) {
                player.sendMessage("§7§m-----x---------------x-----");
                player.sendMessage(" ");
                try {
                    player.sendMessage(PSM.Prefix + "§7Verbunden§8: " + (PSM.getMySQL().getConnection().isClosed() ? "§c✕" : "§a✔"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                player.sendMessage(" ");
                player.sendMessage("§7§m-----x---------------x-----");
            } else {
                player.sendMessage(PSM.Prefix + "§cDieser Command existiert nicht!");
            }
        }
    }
}
