package de.hunjy.commands;

import de.hunjy.PSM;
import de.hunjy.utils.commands.PSMCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
    public void execute(Player player, String[] args) {
        if(player.hasPermission("psm.admin")) {
            if(args.length == 0) {
                player.sendMessage(PSM.Prefix + "§aJa");
            } else {
                player.sendMessage(PSM.Prefix + "§cNein");
            }
        }
    }
}
