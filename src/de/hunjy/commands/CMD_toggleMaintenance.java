package de.hunjy.commands;
/*
    Create by RiedCrafter on 04.10.2019
    @author: RiedCrafter
    @date: 04.10.2019
    @time: 17:09
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import de.hunjy.utils.commands.PSMCommand;
import de.hunjy.utils.random.RandomUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CMD_toggleMaintenance implements PSMCommand {
    @Override
    public String getName() {
        return "togglemaintenance";
    }

    @Override
    public String getDescription() {
        return "Verstellt die Wartungsarbeiten";
    }

    @Override
    public void execute(Player player, String[] args) {
        if(player.hasPermission("psm.admin")) {
            if(args.length == 1) {
                if((boolean)PSM.getInstance().getMainConfig().get("maintenanceMode") == true) {
                    PSM.getInstance().getMainConfig().set("maintenanceMode", false);
                    player.sendMessage(PSM.Prefix + "§7Der §8'§bMaintenance§7-§bMode§8' §7wurde deaktiviert§8.");
                } else if((boolean)PSM.getInstance().getMainConfig().get("maintenanceMode") == false) {
                    PSM.getInstance().getMainConfig().set("maintenanceMode", true);
                    player.sendMessage(PSM.Prefix + "§7Der §8'§bMaintenance§7-§bMode§8' §7wurde aktiviert§8.");
                    Bukkit.getOnlinePlayers().forEach(online -> {
                        if(!online.hasPermission("psm.admin")) {
                            online.kickPlayer("§7§m-----x---------------x-----\n\n§cZur Zeit werden §4Wartungen\n§cdurchgeführt. Bitte habe Geduld.\n\n§7§m-----x---------------x-----");
                        }
                    });
                } else {
                    player.sendMessage(PSM.Prefix + "§cEs trat ein fehler auf. §7(§e/rl§7)");
                }
            } else {
                player.sendMessage(PSM.Prefix + "§cDieser Command existiert nicht!");
            }
        }
    }

}
