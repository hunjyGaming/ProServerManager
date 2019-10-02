package de.hunjy.utils.alert;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/*
    Create by hunjy on 02.10.2019
    @auther: hunjy
    @date: 02.10.2019
    @time: 23:50
    @projekt: ProServerManager
*/
public class Alert {

    AlertType alertType;
    String alertMessage;

    public Alert() {
    }

    public Alert(AlertType alertType) {
        this.alertType = alertType;
    }


    public Alert(AlertType alertType, String alertMessage) {
        this.alertMessage = alertMessage;
        this.alertType = alertType;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void send() {
         Bukkit.getOnlinePlayers().forEach(player -> {
             player.sendMessage(" ");
             player.sendMessage(alertType.getPrefix() + alertMessage);
             player.sendMessage(" ");
         });
    }
    public void sendToPlayer(Player player) {
            player.sendMessage(" ");
            player.sendMessage(alertType.getPrefix() + alertMessage);
            player.sendMessage(" ");
    }


}
