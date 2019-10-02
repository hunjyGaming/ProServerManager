package de.hunjy.utils.commands;

import com.google.common.collect.Lists;
import de.hunjy.PSM;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/*
    Create by hunjy on 30.09.2019
    @auther: hunjy
    @date: 30.09.2019
    @time: 12:13
    @projekt: ProServerManager
*/
public class CMD_PSM implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String lable, String[] args) {

            if( ! (cs instanceof Player)) {return false;}
            Player player = (Player) cs;
            String msg = "";

            if(args.length == 0) {
                msg = "help ";
            }else {
                for (int i = 0; i < args.length; i++) {
                    msg += args[i] + " ";
                }
                msg = msg.substring(0, (msg.length() - 1));
            }

            if(PSMCommandHandler.executeCommand(player, msg)) {
                return true;
            } else {
                player.sendMessage(PSM.Prefix + "§cDieser Command existiert nicht!");
            }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command command, String lable, String[] args) {
        if(args.length >= 1){
            if(cs instanceof Player){
                List<String> list = new ArrayList<>();

                for(PSMCommand psmCMD : PSMCommandHandler.getCommands()) {
                   list.add(psmCMD.getName());
                }

                List<String> matches = Lists.newArrayList();

                if(list == null){
                    list = Lists.newArrayList();
                }

                String search = args[0].toLowerCase();

                for(String s : list){
                    if(s.toLowerCase().startsWith(search)){
                        matches.add(s);
                    }

                }

                return matches;
            }
        }
        return null;
    }
}

