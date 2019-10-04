package de.hunjy.commands;

import de.hunjy.manager.LizenzManager;
import de.hunjy.utils.alert.Alert;
import de.hunjy.utils.alert.AlertType;
import de.hunjy.utils.commands.PSMCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/*
    Create by hunjy on 04.10.2019
    @auther: hunjy
    @date: 04.10.2019
    @time: 15:14
    @projekt: ProServerManager
*/
public class CMD_aktivateLizenz implements PSMCommand {

    @Override
    public String getName() {
        return "activate";
    }

    @Override
    public String getDescription() {
        return "Aktiviere deine Lizenz!";
    }

    @Override
    public void execute(Player player, String[] args) {
        LizenzManager lizenzManager = new LizenzManager();
        if(lizenzManager.isAktiv()) {
            new Alert(AlertType.INFO , "Alle funktionen des Servers werden neu geladen!").send();;
            Bukkit.getServer().reload();
            new Alert(AlertType.SUCCESS , "Alle Funktionen des Servers wurden freigeschaltet!").send();;
        }
    }
}