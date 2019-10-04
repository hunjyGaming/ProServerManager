package de.hunjy.commands;

import de.hunjy.PSM;
import de.hunjy.utils.commands.PSMCommand;
import org.bukkit.entity.Player;

/*
    Create by hunjy on 04.10.2019
    @auther: hunjy
    @date: 04.10.2019
    @time: 14:34
    @projekt: ProServerManager
*/
public class CMD_lizenz implements PSMCommand {

    @Override
    public String getName() {
        return "lizenz";
    }

    @Override
    public String getDescription() {
        return "Zeigt deinen momentane Lizenz";
    }

    @Override
    public void execute(Player player, String[] args) {
        if(PSM.SecureMode) {

            player.sendMessage("§7§m-----x---------------x-----");
            player.sendMessage(" ");
            player.sendMessage(PSM.Prefix + "§4Die Lizenz ist nicht gültig!");
            player.sendMessage(" ");
            player.sendMessage("§7§m-----x---------------x-----");
        }else {
            player.sendMessage("§7§m-----x---------------x-----");
            player.sendMessage(" ");
            player.sendMessage(PSM.Prefix + "§a" + PSM.getInstance().getLizenzManager().getLizenz());
            player.sendMessage(" ");
            player.sendMessage("§7§m-----x---------------x-----");
        }
    }
}
