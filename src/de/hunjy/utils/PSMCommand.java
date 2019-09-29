package de.hunjy.utils;
/*
    Create by RiedCrafter on 29.09.2019
    @author: RiedCrafter
    @date: 29.09.2019
    @time: 20:31
    @projekt: ProServerManager
*/

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PSMCommand implements CommandExecutor {

    private String name;
    private ArrayList<String> commands;

    public PSMCommand(String name) {
        if(name.equals("test")) {
            return;
        }
        this.name = name;
        if(!this.commands.contains(name.toLowerCase())) {
            this.commands.add(name.toLowerCase());
        }
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {

        if(!(cs instanceof Player)) {
            cs.sendMessage("");
            return false;
        }

        return false;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public String getName() {
        return name;
    }
}
