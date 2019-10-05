package de.hunjy.commands;
/*
    Create by RiedCrafter on 04.10.2019
    @author: RiedCrafter
    @date: 04.10.2019
    @time: 21:09
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import de.hunjy.utils.commands.PSMCommand;
import org.bukkit.entity.Player;

public class CMD_setmotd implements PSMCommand {
    @Override
    public String getName() {
        return "setmotd";
    }

    @Override
    public String getDescription() {
        return "Setzt die Motd";
    }

    @Override
    public void execute(Player player, String[] args) {
        if(player.hasPermission("psm.admin")) {
            if(args.length == 1) {
                player.sendMessage(PSM.Prefix + "§7/psm setmotd <1,2> <Text...>");
            } else if(args.length >= 3) {
                if(args[1].equalsIgnoreCase("1")) {
                    StringBuilder sB = new StringBuilder();
                    for(int i = 2; i < args.length; i++) {
                        sB.append(args[i]).append(" ");
                    }
                    PSM.getInstance().getMainConfig().set("motd1", sB.toString());
                    player.sendMessage(PSM.Prefix + "§7Die §e1 §7Linie der Motd wurde erfolgreich §aangepasst§8.");
                } else if(args[1].equalsIgnoreCase("2")) {
                    StringBuilder sB = new StringBuilder();
                    for(int i = 2; i < args.length; i++) {
                        sB.append(args[i]).append(" ");
                    }
                    PSM.getInstance().getMainConfig().set("motd2", sB.toString());
                    player.sendMessage(PSM.Prefix + "§7Die §e2 §7Linie der Motd wurde erfolgreich §aangepasst§8.");
                } else {
                    player.sendMessage(PSM.Prefix + "§7/psm setmotd <1,2> <Text...>");
                }
            } else {
                player.sendMessage(PSM.Prefix + "§cDieser Command existiert nicht!");
            }
        }
    }
}
