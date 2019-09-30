package de.hunjy.commands;

import de.hunjy.utils.commands.PSMCommand;
import de.hunjy.utils.commands.PSMCommandHandler;
import org.bukkit.entity.Player;

/*
    Create by hunjy on 29.09.2019
    @auther: hunjy
    @date: 29.09.2019
    @time: 15:48
    @projekt: ProServerManager
*/
public class CMD_help implements PSMCommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Zeigt alle Befehle";
    }

    @Override
    public void execute(Player player, String[] args) {
        if(player.hasPermission("psm.admin")) {
            for(PSMCommand command : PSMCommandHandler.getCommands()) {
                player.sendMessage("§3/psm " + command.getName() + " §8» §7" + command.getDescription());
            }
        }
    }
}
