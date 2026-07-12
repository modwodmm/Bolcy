package com.modwodmm.bolcy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args){

        //Creates necessary objects
        final ObjectMapper objectMapper = new ObjectMapper();
        final JsonHandler jsonHandler = new JsonHandler(objectMapper);
        final AuthHandler authHandler = new AuthHandler(jsonHandler);
        final ConfigManager configManager = new ConfigManager();
        final ServerSockets serverSockets = new ServerSockets(objectMapper, authHandler, configManager);

        //Starts the server-side
        try{
            serverSockets.start();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
