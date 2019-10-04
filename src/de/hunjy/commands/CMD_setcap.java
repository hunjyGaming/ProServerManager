package de.hunjy.commands;
/*
    Create by RiedCrafter on 04.10.2019
    @author: RiedCrafter
    @date: 04.10.2019
    @time: 18:23
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import de.hunjy.utils.commands.PSMCommand;
import org.bukkit.entity.Player;

public class CMD_setcap implements PSMCommand {
    @Override
    public String getName() {
        return "setcap";
    }

    @Override
    public String getDescription() {
        return "Setzt die Spieleranzahl";
    }

    @Override
    public void execute(Player player, String[] args) {
        if(player.hasPermission("psm.admin")) {
            if(args.length == 1) {
                player.sendMessage(PSM.Prefix + "§7/psm setcap <hard|soft> <Anzahl>");
            } else if(args.length == 3) {
                if(args[1].equalsIgnoreCase("hard")) {
                    int hardcap = 0;
                    try {
                        hardcap = Integer.parseInt(args[2]);
                        if(hardcap > 0) {
                            if(hardcap >= (int)PSM.getInstance().getMainConfig().get("softCap")) {
                                PSM.getInstance().getMainConfig().set("hardCap", hardcap);
                                player.sendMessage(PSM.Prefix + "§7Die §8'§bHardcap§8' §7beträgt nun §a" + hardcap + "§8.");
                            } else {
                                player.sendMessage(PSM.Prefix + "§cDie Zahl muss mindestens genau so groß wie die Softcap sein.");
                            }
                        } else {
                            player.sendMessage(PSM.Prefix + "§cDie Zahl muss größer als 0 sein.");
                        }
                    } catch (NumberFormatException e) {
                        player.sendMessage(PSM.Prefix + "§cDu musst eine Zahl angeben.");
                    }
                } else if(args[1].equalsIgnoreCase("soft")) {
                    int softcap = 0;
                    try {
                        softcap = Integer.parseInt(args[2]);
                        if(softcap > 0) {
                            if(softcap <= (int)PSM.getInstance().getMainConfig().get("hardCap")) {
                                PSM.getInstance().getMainConfig().set("softCap", softcap);
                                player.sendMessage(PSM.Prefix + "§7Die §8'§bSoftcap§8' §7beträgt nun §a" + softcap + "§8.");
                            } else {
                                player.sendMessage(PSM.Prefix + "§cDie Zahl darf maximal genau so groß wie die Hardcap sein.");
                            }
                        } else {
                            player.sendMessage(PSM.Prefix + "§cDie Zahl muss größer als 0 sein.");
                        }
                    } catch (NumberFormatException e) {
                        player.sendMessage(PSM.Prefix + "§cDu musst eine Zahl angeben.");
                    }
                } else {
                    player.sendMessage(PSM.Prefix + "§7/psm setcap <hard|soft> <Anzahl>");
                }
            } else {
                player.sendMessage(PSM.Prefix + "§cDieser Command existiert nicht!");
            }
        }
    }
}
