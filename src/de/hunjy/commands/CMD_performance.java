package de.hunjy.commands;

import de.hunjy.utils.commands.PSMCommand;
import org.bukkit.entity.Player;

/*
    Create by hunjy on 30.09.2019
    @auther: hunjy
    @date: 30.09.2019
    @time: 14:10
    @projekt: ProServerManager
*/
public class CMD_performance implements PSMCommand{

    @Override
    public String getDescription() {
        return "Zeigt die Leistung des Servers";
    }

    @Override
    public String getName() {
        return "performance";
    }

    @Override
    public void execute(Player player, String[] args) {

    }
}
