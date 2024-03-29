package de.hunjy.commands;

import de.hunjy.PSM;
import de.hunjy.manager.TPSManager;
import de.hunjy.utils.commands.PSMCommand;
import org.bukkit.entity.Player;

import java.lang.management.ManagementFactory;
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
                player.sendMessage("§7§m-----x---------------x-----");
                player.sendMessage(" ");
                double TPS = TPSManager.getTPS();
                DecimalFormat TpsFormat = new DecimalFormat("#.##");

                String TPSolor ;
                if(TPS >= 20){
                    TPSolor = "§2";
                }

                else if(TPS > 18){
                    TPSolor = "§a";
                }

                else if(TPS > 13){
                    TPSolor = "§6";
                }

                else if(TPS > 9){
                    TPSolor = "§c";
                }

                else {
                    TPSolor = "§4";
                }


                double TPSUsed = TPS;
                double TPSMax = 20;
                double TPSProcent = (TPSUsed*100) / TPSMax;

                TPSProcent *= 100d;
                TPSProcent = ((int)TPSProcent) / 100d;
                player.sendMessage(PSM.Prefix + "§7TPS§8: " + TPSolor +  TpsFormat.format(TPS) + " §8/ §220 §7(§b" + TpsFormat.format(TPSProcent) + "%§7)");


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

                String OS = ManagementFactory.getOperatingSystemMXBean().getName();
                String Ver = ManagementFactory.getOperatingSystemMXBean().getVersion();

                String CPUColor;
                double CPUUsage = ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
                double CPUProcent = (Math.round(CPUUsage * 100));
                if(CPUProcent >= 90) {
                    CPUColor = "§4";
                }else if(CPUProcent >= 75) {
                    CPUColor = "§c";
                }else if(CPUProcent >= 50) {
                    CPUColor = "§6";
                }else if(CPUProcent >= 25) {
                    CPUColor = "§a";
                }else {
                    CPUColor = "§2";
                }

                player.sendMessage( PSM.Prefix + "§7RAM§8: " + RAMColor + (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1024L / 1024L + " §8/ §4"  + Runtime.getRuntime().maxMemory()  / 1024L / 1024L + " §7(§b" + TpsFormat.format(RamProcent) + "%§7)");
                player.sendMessage( PSM.Prefix + "§7CPU§8: " + CPUColor + CPUProcent + "§7%  §7(§b" + ((Runtime.getRuntime().availableProcessors() == 1) ? ("1 Kern") : (Runtime.getRuntime().availableProcessors() + " Kerne")) + "§7)");
                player.sendMessage(PSM.Prefix + "§7OS§8: §e" + OS + " §7(§b" + Ver + "§7)");
                player.sendMessage(" ");
                player.sendMessage("§7§m-----x---------------x-----");
            } else {
                player.sendMessage(PSM.Prefix + "§cDieser Command existiert nicht!");
            }
        }
    }
}
