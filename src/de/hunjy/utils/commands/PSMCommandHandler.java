package de.hunjy.utils.commands;
/*
    Create by RiedCrafter on 30.09.2019
    @author: RiedCrafter
    @date: 30.09.2019
    @time: 13:10
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PSMCommandHandler {

    private static List<PSMCommand> commands = new ArrayList<>();

    public static void registerCommand(PSMCommand psmCommand) {
        commands.add(psmCommand);

        if(Arrays.asList(psmCommand.getClass().getInterfaces()).contains(Listener.class)) {
            Bukkit.getPluginManager().registerEvents((Listener) psmCommand, PSM.getInstance());
        }
    }

    public static boolean executeCommand(Player player, String command) {
        String rawCommand = command;
        String[] args = rawCommand.split(" ");

        for(PSMCommand psmCommand : commands) {
            if(rawCommand.split(" ")[0].equalsIgnoreCase(psmCommand.getName())) {
                psmCommand.execute(player, args);
                return true;
            }
        }
        return false;
    }

    public static List<PSMCommand> getCommands() {
        return commands;
    }
}
