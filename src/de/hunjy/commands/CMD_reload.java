package de.hunjy.commands;
/*
    Create by RiedCrafter on 29.09.2019
    @author: RiedCrafter
    @date: 29.09.2019
    @time: 15:52
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_reload implements CommandExecutor {

    private String alias;

    public CMD_reload(PSM instance, String alias) {
        instance.getCommand("PSM").setExecutor(this);
        this.alias = alias;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String lable, String[] args) {


        if (!(cs instanceof Player)) {
            return false;
        }

        Player player = (Player) cs;

        if (args.length == 0) {
            return false;
        }

        if (args[0].equalsIgnoreCase(alias)) {
            player.sendMessage("§aJa 1");
        } else {
            player.sendMessage("§cNein 1");
        }
        return false;
    }
}
