package de.hunjy;

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

    @Override
    public void onEnable() {

        instance = this;

        initCommands();
        initListener();
    }

    @Override
    public void onDisable() {

    }

    private void initCommands() {

    }
    private void initListener() {

    }

    public static PSM getInstance() {
        return instance;
    }
}
