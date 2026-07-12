package com.modwodmm.bolcy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private final Properties properties = new Properties();
    private String host;
    private int port;

    public ConfigManager(){
        try{
            FileInputStream input = new FileInputStream("data/config.properties");
            properties.load(input);
            host = properties.getProperty("host");
            port = Integer.parseInt(properties.getProperty("port"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Host getter
    public String getHost(){
        return this.host;
    }

    //Port getter
    public int getPort(){
        return this.port;
    }

}
