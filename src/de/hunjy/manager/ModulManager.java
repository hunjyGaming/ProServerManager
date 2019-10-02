package de.hunjy.manager;

import de.hunjy.PSM;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;

/*
    Create by hunjy on 30.09.2019
    @auther: hunjy
    @date: 30.09.2019
    @time: 14:19
    @projekt: ProServerManager
*/
public class ModulManager {

    public File file = new File(PSM.getInstance().getDataFolder() + "//moduls");

    public void loadAllModuls() {
    try {
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                if (f.getName().contains(".jar")) {
                    Plugin plugin = Bukkit.getPluginManager().loadPlugin(f);
                    plugin.onEnable();
                }
            }
        }
    }catch (Exception e) {
        e.printStackTrace();
    }

    }

}
