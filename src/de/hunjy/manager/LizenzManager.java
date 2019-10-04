package de.hunjy.manager;


import de.hunjy.PSM;
import de.hunjy.utils.alert.Alert;
import de.hunjy.utils.alert.AlertType;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Random;

/*
    Create by hunjy on 04.10.2019
    @auther: hunjy
    @date: 04.10.2019
    @time: 00:02
    @projekt: ProServerManager
*/
public class LizenzManager {

    private String lizenz;

    private long lastUpdate;

    public LizenzManager() {
        createLizenz();


        File file = new File(PSM.getInstance().getDataFolder(), "LIZENZ_KEY.txt");
        long timeStamp = file.lastModified();
        this.lastUpdate = timeStamp;

        try {
            getLizenz(new URL("http://dev.hunjy.de/"));
        }catch (MalformedURLException e)  {
        }catch(IOException e) {
        }

        if(!checkLizenz()) {
            PSM.SecureMode = true;
            new Alert(AlertType.DANGER, "Die Lizenz ist nicht gültig!").send();
            new Alert(AlertType.DANGER, "Bitte benutze /psm activate").send();
        }
    }

    public boolean isFileUpdated() {
        File file = new File(PSM.getInstance().getDataFolder(), "LIZENZ_KEY.txt");
        long timeStamp = file.lastModified();

        if( this.lastUpdate != timeStamp ) {
            this.lastUpdate = timeStamp;
            return true;
        }
        return false;
    }
    private void createLizenz() {
        File file = new File(PSM.getInstance().getDataFolder(), "LIZENZ_KEY.txt");
        if(!file.exists()) {
            try {
                PrintWriter writer = new PrintWriter(file, "UTF-8");
                Random random = new Random();
                String key = String.format("PSM-%04d-%04d-%04d", random.nextInt(10000), random.nextInt(10000), random.nextInt(10000));
                writer.println(key);
                writer.close();
            }catch (FileNotFoundException e){}
            catch (UnsupportedEncodingException e){}
        }else {
            return;
        }
    }

    private void getLizenz(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        if(connection.getResponseCode() == 200) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            while (line != null) {
                if (line.split("/")[0].equalsIgnoreCase(PSM.getInstance().getServer().getIp())) {
                    lizenz = line.split("/")[1];

                    return;
                }

                line = reader.readLine();
            }
        }
    }

    private boolean checkLizenz() {

        String key = "";

        File file = new File(PSM.getInstance().getDataFolder(), "LIZENZ_KEY.txt");
        try (BufferedReader br = Files.newBufferedReader(file.toPath())) {
        key = br.readLine();
        } catch (IOException e) {
        }
        if(key.equals(lizenz)) {
            return true;
        }else {
            return false;
        }
    }

    public String getLizenz() {
        return lizenz;
    }

    public boolean isAktiv() {
        try {
            getLizenz(new URL("http://dev.hunjy.de/"));
        }catch (MalformedURLException e)  {
        }catch(IOException e) {
        }
        return  checkLizenz();
    }
}
