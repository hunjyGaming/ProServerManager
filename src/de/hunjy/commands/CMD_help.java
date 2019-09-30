package de.hunjy.commands;

import de.hunjy.PSM;
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
public class CMD_help implements CommandExecutor {

    private String alias;

    public CMD_help(PSM instance, String alias) {
        instance.getCommand("PSM").setExecutor(this);
        this.alias = alias;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String lable, String[] args) {


        if(! ( cs instanceof  Player) ) {
            return false;
        }

        Player player = (Player) cs;

        if(args.length == 0) {
            return false;
        }

        if(args[0].equalsIgnoreCase(alias)){
            player.sendMessage("§aJa");
        }else {
            player.sendMessage("§cNein");
        }
        return false;
    }
}
