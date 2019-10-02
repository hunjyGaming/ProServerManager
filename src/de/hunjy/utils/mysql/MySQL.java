package de.hunjy.utils.mysql;
/*
    Create by RiedCrafter on 02.10.2019
    @author: RiedCrafter
    @date: 02.10.2019
    @time: 20:24
    @projekt: ProServerManager
*/

import de.hunjy.PSM;
import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL {

    private String HOST, PORT, USER, DATABASE, PASSWORD;
    private boolean AUTORECONNECT;
    private Connection connection;

    public MySQL(String host, String port, String user, String database, String password, boolean autoreconnect) {
        HOST = host;
        PORT = port;
        USER = user;
        DATABASE = database;
        PASSWORD = password;
        AUTORECONNECT = autoreconnect;
        connect();
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?autoReconnect=" + AUTORECONNECT, USER, PASSWORD);
            Bukkit.getConsoleSender().sendMessage(PSM.Prefix + "§aDie MySQL-Datenbankverbindung wurde erfolgreich aufgebaut.");
        } catch(SQLException e) {
            connection = null;
            Bukkit.getConsoleSender().sendMessage(PSM.Prefix + "§cDie MySQL-Daten weisen fehler auf. §7[§bmysql§8.§byml§7]");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if(connection != null) {
                connection.close();
                Bukkit.getConsoleSender().sendMessage(PSM.Prefix + "§aDie MySQL-Datenbankverbindung wurde erfolgreich geschlossen.");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String qry) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(qry);
            statement.close();
        } catch(SQLException e) {
            connect();
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet(String qry) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(qry);
        } catch(SQLException e) {
            connect();
            e.printStackTrace();
        }

        return resultSet;
    }

    public Connection getConnection() {
        return connection;
    }

}
