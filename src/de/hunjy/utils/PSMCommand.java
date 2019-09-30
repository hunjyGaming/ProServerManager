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

public abstract class PSMCommand {

    private String alias;
    private String description;

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract void execute(Object... args);

}
