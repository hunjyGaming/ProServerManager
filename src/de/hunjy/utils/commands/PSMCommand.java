package de.hunjy.utils.commands;
/*
    Create by RiedCrafter on 30.09.2019
    @author: RiedCrafter
    @date: 30.09.2019
    @time: 13:10
    @projekt: ProServerManager
*/

import org.bukkit.entity.Player;

public interface PSMCommand {

    public String getName();
    public void execute(Player player, String[] args);

}
