package de.hunjy.manager;


import de.hunjy.PSM;
import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;

/*
    Create by hunjy on 29.09.2019
    @auther: hunjy
    @date: 29.09.2019
    @time: 14:48
    @projekt: ProServerManager
*/
public class MessageManager {

    private File path;
    private String pathString;
    private long lastUpdate;

    public MessageManager(String pathString) {
        this.pathString = pathString;
        this.path = new File(PSM.getInstance().getDataFolder() + "/messages", pathString);
        if(!this.path.exists()) {
            Bukkit.getConsoleSender().sendMessage("§8§l[§4§l!§8§l] §cDie Datei " + pathString + " wurde nicht gefunden!");
        }
    }


    private boolean isFileUpdated() {
        long timeStamp = path.lastModified();

        if( this.lastUpdate != timeStamp ) {
            this.lastUpdate = timeStamp;
            return true;
        }
        return false;
    }

    public StringBuilder get(String key) {

        if(isFileUpdated()) {
            this.path = null;
            this.path = new File(pathString);
        }

        try {
            JSONParser jsonParser = new JSONParser();
            Object parsed = jsonParser.parse(this.path.getPath());
            JSONObject jsonObject = (JSONObject)parsed;
            return new StringBuilder().append(jsonObject.get(key));
        }catch (ParseException e) {
            return null;
        }
    }

}
