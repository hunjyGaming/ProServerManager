package de.hunjy.utils.mysql;
/*
    Create by RiedCrafter on 02.10.2019
    @author: RiedCrafter
    @date: 02.10.2019
    @time: 20:30
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import de.hunjy.utils.file.FileWriter;

public class MySQL_Config {

    private FileWriter fileWriter;
    private String host = "localhost";
    private String port = "3306";
    private String user = "root";
    private String database = "PSM";
    private String password = "password";


    public MySQL_Config(final PSM psm) {
        fileWriter = new FileWriter(psm.getDataFolder().getPath(), "mysql.yml");
        load();
        read();
    }

    private void load() {
        fileWriter.setDefaultValue("MySQL.host", host);
        fileWriter.setDefaultValue("MySQL.port", port);
        fileWriter.setDefaultValue("MySQL.user", user);
        fileWriter.setDefaultValue("MySQL.database", database);
        fileWriter.setDefaultValue("MySQL.password", password);
        fileWriter.save();
    }

    private void read() {
        setHost(fileWriter.getString("MySQL.host"));
        setPort(fileWriter.getString("MySQL.port"));
        setUser(fileWriter.getString("MySQL.user"));
        setDatabase(fileWriter.getString("MySQL.database"));
        setPassword(fileWriter.getString("MySQL.password"));
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setDatabase(String database) {
        this.database = database;
    }


}
