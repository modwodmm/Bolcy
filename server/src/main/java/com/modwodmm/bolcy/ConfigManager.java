package com.modwodmm.bolcy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private final Properties properties = new Properties();
    private int port;

    public ConfigManager(){
        try{
            FileInputStream input = new FileInputStream("data/config.properties");
            properties.load(input);
            port = Integer.parseInt(properties.getProperty("server.port"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Port getter
    public int getPort(){
        return this.port;
    }
}
