package de.hunjy.manager;

import de.hunjy.PSM;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/*
    Create by hunjy on 29.09.2019
    @auther: hunjy
    @date: 29.09.2019
    @time: 15:13
    @projekt: ProServerManager
*/
public class ConfigManager {

    private File file;
    private String name;
    private YamlConfiguration config;
    private long lastUpdate;

    public ConfigManager(String name) {
        this.file = new File(PSM.getInstance().getDataFolder(), name);
        this.name = name;
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }


    private void fileUpdate() {
        long timeStamp = file.lastModified();

        if( this.lastUpdate != timeStamp ) {
            this.lastUpdate = timeStamp;
            this.file = new File(PSM.getInstance().getDataFolder(), this.name);
            this.config = YamlConfiguration.loadConfiguration(this.file);
        }
    }



    private void save() {
        try {
            config.save(file);
        }catch (IOException e){
        }
    }


    public void setDefault(String key, Object value) {
        if(!file.exists()) {
            config.set(key ,value);
            save();
        }
    }

    public void set(String key, Object value) {
        fileUpdate();
        config.set(key, value);
        save();
    }

    public Object get(String key) {
        fileUpdate();
        return config.get(key);
    }


}
