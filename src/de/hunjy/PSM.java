package de.hunjy;

import de.hunjy.manager.ConfigManager;
import de.hunjy.manager.MessageManager;
import org.bukkit.plugin.java.JavaPlugin;

/*
    Create by hunjy on 29.09.2019
    @auther: hunjy
    @date: 29.09.2019
    @time: 14:43
    @projekt: ProServerManager
*/
public class PSM extends JavaPlugin {

    static PSM instance;


    private MessageManager messageManager;
    private ConfigManager mainConfig;



    @Override
    public void onEnable() {

        instance = this;

        initCommands();
        initListener();
        initManager();
    }

    @Override
    public void onDisable() {

    }

    private void initManager() {
        mainConfig = new ConfigManager("config.yml");
        mainConfig.setDefault("messageFile", "de.json");


        messageManager = new MessageManager( ( String ) mainConfig.get("messageFile"));
    }

    private void initCommands() {

    }

    private void initListener() {

    }

    public String raplaceVars(String s, String var, String entry) {

        s = s.replaceAll(var, entry);

        return s;
    }


    public static PSM getInstance() {
        return instance;
    }

    public ConfigManager getMainConfig() {
        return mainConfig;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}
