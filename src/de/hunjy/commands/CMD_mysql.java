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
import de.hunjy.utils.mysql.MySQL;
import de.hunjy.utils.mysql.MySQL_Config;
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
                    if (PSM.getMySQL().getConnection() == null) {
                        player.sendMessage(PSM.Prefix + "§7Verbunden§8: §c✕");
                    } else {
                        player.sendMessage(PSM.Prefix + "§7Verbunden§8: §a✔");
                    }
                player.sendMessage(PSM.Prefix + "§7HOST§8: §b" + PSM.getMySQL_config().getHost());
                player.sendMessage(PSM.Prefix + "§7PORT§8: §b" + PSM.getMySQL_config().getPort());
                player.sendMessage(PSM.Prefix + "§7USER§8: §b" + PSM.getMySQL_config().getUser());
                player.sendMessage(PSM.Prefix + "§7DATABASE§8: §b" + PSM.getMySQL_config().getDatabase());
                player.sendMessage(PSM.Prefix + "§7PASSWORD§8: §b" + PSM.getMySQL_config().getPassword());
                player.sendMessage(" ");
                player.sendMessage("§7§m-----x---------------x-----");
            } else {
                player.sendMessage(PSM.Prefix + "§cDieser Command existiert nicht!");
            }
        }
    }
}
