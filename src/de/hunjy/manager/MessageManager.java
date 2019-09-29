package de.hunjy.manager;


import de.hunjy.PSM;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

/*
    Create by hunjy on 29.09.2019
    @auther: hunjy
    @date: 29.09.2019
    @time: 14:48
    @projekt: ProServerManager
*/
public class MessageManager {

    private File file;
    private String name;
    private YamlConfiguration config;
    private String pathString;
    private long lastUpdate;

    public MessageManager(String name) {
        this.name = name;
        this.file = new File(PSM.getInstance().getDataFolder() + "/messages", name);
        this.config = YamlConfiguration.loadConfiguration(this.file);
        if(!this.file.exists()) {
            Bukkit.getConsoleSender().sendMessage("§8§l[§4§l!§8§l] §cDie Datei " + name + " wurde nicht gefunden!");
        }
    }

    private void save() {
        try {
            config.save(file);
        }catch (IOException e){
        }
    }

    private boolean isFileUpdated() {
        long timeStamp = file.lastModified();

        if( this.lastUpdate != timeStamp ) {
            this.lastUpdate = timeStamp;
            return true;
        }
        return false;
    }


    public void setDefault(String key, Object value) {
        if(!config.contains(key)) {
            config.set(key ,value);
            save();
        }
    }

    public StringBuilder get(String key) {

        if(isFileUpdated()) {
            this.config = null;
            this.file = new File(PSM.getInstance().getDataFolder() + "/messages", name);
            this.config = YamlConfiguration.loadConfiguration(this.file);
        }
        return new StringBuilder().append(config.getString(key));
    }

}
