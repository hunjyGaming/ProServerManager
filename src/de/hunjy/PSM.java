package de.hunjy;

import de.hunjy.commands.CMD_help;
import de.hunjy.commands.CMD_performance;
import de.hunjy.listener.EVENT_JoinQuit;
import de.hunjy.manager.ConfigManager;
import de.hunjy.manager.MessageManager;
import de.hunjy.manager.TPSManager;
import de.hunjy.utils.PrefixBuilder;
import de.hunjy.utils.alert.Alert;
import de.hunjy.utils.alert.AlertType;
import de.hunjy.utils.commands.CMD_PSM;
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

    private static MySQL mySQL;
    private static MySQL_Config mySQL_config;
    private MessageManager messageManager;
    private ConfigManager mainConfig;
    public static String Prefix = new PrefixBuilder("§3PSM").build();


    @Override
    public void onEnable() {

        instance = this;
        initCommands();
        initListener();
        initManager();
        initRunnables();
        startTPSAlert();
    }

    @Override
    public void onDisable() {

    }

    private void initMySQL() {
        mySQL = new MySQL(mySQL_config.getHost(), mySQL_config.getPort(), mySQL_config.getUser(), mySQL_config.getDatabase(), mySQL_config.getPassword(), true);
    }

    private void initManager() {
        mainConfig = new ConfigManager("config.yml");
        mainConfig.setDefault("messageFile", "messages.yml");
        mainConfig.setDefault("enableJoinMessage", true);
        mainConfig.setDefault("JoinMessage", "&8[&a+&8] §7%player%");
        mainConfig.setDefault("joinePermission", "psm.join.info");


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

    public static MySQL getMySQL() {
        return mySQL;
    }




    private void startTPSAlert() {
            Bukkit.getScheduler().runTaskTimerAsynchronously(getInstance(), new Runnable() {
                @Override
                public void run() {
                    double TPS = TPSManager.getTPS();
                    if (TPS < 15 && TPS > 10) {
                        new Alert(AlertType.WARNING, "§7Die Tps ist unter §c15 TPS§7!").send();
                    } else if (TPS < 15 && TPS < 10) {
                        new Alert(AlertType.DANGER, "§7Die Tps ist unter §410 TPS§7!").send();
                    }
                }
            }, 20, 20);
    }
}
