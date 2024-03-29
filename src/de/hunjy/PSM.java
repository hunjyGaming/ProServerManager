package de.hunjy;

import de.hunjy.commands.*;
import de.hunjy.listener.EVENT_ListPing;
import de.hunjy.manager.*;
import de.hunjy.utils.commands.CMD_PSM;
import de.hunjy.listener.EVENT_JoinQuit;
import de.hunjy.utils.PrefixBuilder;
import de.hunjy.utils.commands.PSMCommandHandler;
import de.hunjy.utils.mysql.MySQL;
import de.hunjy.utils.mysql.MySQL_Config;
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

    public static boolean SecureMode = false;

    private static MySQL mySQL;
    private static MySQL_Config mySQL_config;
    private MessageManager messageManager;
    private ConfigManager mainConfig;
    private LizenzManager lizenzManager;

    public static String Prefix = new PrefixBuilder("§3PSM").build();


    @Override
    public void onEnable() {

        instance = this;
        lizenzManager = new LizenzManager();

        if(SecureMode) {
            getCommand("psm").setExecutor(new CMD_PSM());
            getCommand("psm").setTabCompleter(new CMD_PSM());
            PSMCommandHandler.registerCommand(new CMD_help());
            PSMCommandHandler.registerCommand(new CMD_info());
            PSMCommandHandler.registerCommand(new CMD_lizenz());
            PSMCommandHandler.registerCommand(new CMD_aktivateLizenz());
        }else {
            initMySQL();
            initCommands();
            initListener();
            initManager();
            initRunnables();
        }
    }

    @Override
    public void onDisable() {

    }

    private void initMySQL() {
        mySQL_config = new MySQL_Config(getInstance());
        mySQL = new MySQL(mySQL_config.getHost(), mySQL_config.getPort(), mySQL_config.getUser(), mySQL_config.getDatabase(), mySQL_config.getPassword(), true);
    }

    private void initManager() {
        mainConfig = new ConfigManager("config.yml");
        mainConfig.setDefault("messageFile", "messages.yml");
        mainConfig.setDefault("enableJoinMessage", true);
        mainConfig.setDefault("enableMySQL", false);
        mainConfig.setDefault("maintenanceMode", false);
        mainConfig.setDefault("secureSystem", true);
        mainConfig.setDefault("JoinMessage", "&8[&a+&8] §7%player%");
        mainConfig.setDefault("joinePermission", "psm.join.info");
        mainConfig.setDefault("hardCap", 110);
        mainConfig.setDefault("softCap", 100);
        mainConfig.setDefault("motd1", "&3ProServerManager&8.&3com &7- &aDein Testnetzwerk");
        mainConfig.setDefault("motd2", "&8[&2+&8] &6Coole Updates &c!!!");
        mainConfig.setDefault("maintenanceMotd1", "&3ProServerManager&8.&3com &7- &aDein Testnetzwerk");
        mainConfig.setDefault("maintenanceMotd2", "&cWir führen aktuell &4Wartungen &cdurch!");

        messageManager = new MessageManager( ( String ) mainConfig.get("messageFile"));
    }

    private void initRunnables() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), new TPSManager(), 100L, 1L);
    }

    private void initCommands() {
        getCommand("psm").setExecutor(new CMD_PSM());
        getCommand("psm").setTabCompleter(new CMD_PSM());
        PSMCommandHandler.registerCommand(new CMD_help());
        PSMCommandHandler.registerCommand(new CMD_performance());
        PSMCommandHandler.registerCommand(new CMD_info());
        PSMCommandHandler.registerCommand(new CMD_mysql());
        PSMCommandHandler.registerCommand(new CMD_lizenz());
        PSMCommandHandler.registerCommand(new CMD_setcap());
        PSMCommandHandler.registerCommand(new CMD_setmotd());
        PSMCommandHandler.registerCommand(new CMD_toggleMaintenance());
    }

    private void initListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EVENT_JoinQuit(), getInstance());
        pm.registerEvents(new CMD_info(), getInstance());
        pm.registerEvents(new EVENT_ListPing(), getInstance());
        pm.registerEvents(new SecureManager(), getInstance());
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

    public static MySQL getMySQL() {
        return mySQL;
    }

    public LizenzManager getLizenzManager() {
        return lizenzManager;
    }

    public static MySQL_Config getMySQL_config() {
        return mySQL_config;
    }
}
