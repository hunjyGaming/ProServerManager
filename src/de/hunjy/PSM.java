package de.hunjy;

import de.hunjy.commands.CMD_performance;
import de.hunjy.utils.commands.CMD_PSM;
import de.hunjy.commands.CMD_help;
import de.hunjy.listener.EVENT_JoinQuit;
import de.hunjy.manager.ConfigManager;
import de.hunjy.manager.MessageManager;
import de.hunjy.utils.PrefixBuilder;
import de.hunjy.utils.commands.PSMCommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
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
    public static String Prefix = new PrefixBuilder("§3PSM").build();


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
        mainConfig.setDefault("messageFile", "messages.yml");
        mainConfig.setDefault("enableJoinMessage", true);
        mainConfig.setDefault("JoinMessage", "&8[&a+&8] §7%player%");
        mainConfig.setDefault("joinePermission", "psm.join.info");


        messageManager = new MessageManager( ( String ) mainConfig.get("messageFile"));
    }

    private void initCommands() {
        getCommand("psm").setExecutor(new CMD_PSM());
        getCommand("psm").setTabCompleter(new CMD_PSM());
        PSMCommandHandler.registerCommand(new CMD_help());
        PSMCommandHandler.registerCommand(new CMD_performance());

    }

    private void initListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EVENT_JoinQuit(), getInstance());

    }

    public String replaceVar(String s, String var, String entry) {

        s = s.replaceAll(var, entry).replaceAll("&", "§");

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
