package de.hunjy;

import de.hunjy.commands.CMD_help;
import de.hunjy.listener.EVENT_JoinQuit;
import de.hunjy.manager.ConfigManager;
import de.hunjy.manager.MessageManager;
import de.hunjy.utils.PrefixBuilder;
import de.hunjy.utils.commands.PSMCommand;
import de.hunjy.utils.commands.PSMCommandHandler;
import de.hunjy.utils.commands.PSMCommandListener;
import net.minecraft.server.v1_12_R1.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

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
    public static String Prefix = new PrefixBuilder("§7[§3PSM§7] ").build();

   // private HashMap<String, CommandExecutor> commandExecutorMap;

    @Override
    public void onEnable() {

        instance = this;
        //commandExecutorMap = new HashMap<>();
        initCommands();
       // updateCommands("reload");
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
        mainConfig.setDefault("Prefix", " &8│ &7%prefix% &8»");


        messageManager = new MessageManager( ( String ) mainConfig.get("messageFile"));
    }

    private void initCommands() {
        PSMCommandHandler.registerCommand(new CMD_help());

    }

    private void initListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EVENT_JoinQuit(), getInstance());
        pm.registerEvents(new PSMCommandListener(), getInstance());

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

   // public HashMap<String, CommandExecutor> getCommandExecutorMap() {
     //   return commandExecutorMap;
   // }

    //public void updateCommands(String s) {
     //   CommandExecutor cmdE = getCommandExecutorMap().getOrDefault(s, new CMD_help("help"));
     //   getCommand("psm").setExecutor(cmdE);

    //}
}
