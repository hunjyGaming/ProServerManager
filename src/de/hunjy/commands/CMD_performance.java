package de.hunjy.commands;

import de.hunjy.PSM;
import de.hunjy.manager.TPSManager;
import de.hunjy.utils.commands.PSMCommand;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

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
        if(player.hasPermission("psm.admin")) {
            if(args.length == 1) {
                double TPS = TPSManager.getTPS();
                DecimalFormat TpsFormat = new DecimalFormat("#.##");

                if(TPS > 20){
                    player.sendMessage(PSM.Prefix + "§7TPS§8: §a" + TpsFormat.format(TPS));
                }

                else if(TPS > 18){
                    player.sendMessage(PSM.Prefix + "§7TPS§8: §2" + TpsFormat.format(TPS));
                }

                else if(TPS > 13){
                    player.sendMessage(PSM.Prefix + "§7TPS§8: §6" + TpsFormat.format(TPS));
                }

                else if(TPS > 9){
                    player.sendMessage(PSM.Prefix + "§7TPS§8: §c" + TpsFormat.format(TPS));
                }

                else if(TPS < 9){
                    player.sendMessage(PSM.Prefix + "§7TPS§8: §4" + TpsFormat.format(TPS));
                }
                String RAMColor;
                long RamUsed = (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1024L / 1024L;
                long RamMax = Runtime.getRuntime().maxMemory() / 1024L / 1024L;
                double RamProcent = (RamUsed*100) / RamMax;
                if(RamProcent >= 90) {
                    RAMColor = "§4";
                }else if(RamProcent >= 75) {
                    RAMColor = "§c";
                }else if(RamProcent >= 50) {
                    RAMColor = "§6";
                }else if(RamProcent >= 25) {
                    RAMColor = "§a";
                }else {
                    RAMColor = "§2";
                }


                player.sendMessage( PSM.Prefix + "§7RAM§8: " + RAMColor + (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1024L / 1024L + " §8/ §4"  + Runtime.getRuntime().maxMemory()  / 1024L / 1024L + " §7(§b" + TpsFormat.format(RamProcent) + "%§7)");
                player.sendMessage( PSM.Prefix + "§7CPU§8: §2" + Runtime.getRuntime().availableProcessors() + " §7(§bKern/e§7)");
            } else {
                player.sendMessage(PSM.Prefix + "§cDieser Command existiert nicht!");
            }
        }
    }
}
