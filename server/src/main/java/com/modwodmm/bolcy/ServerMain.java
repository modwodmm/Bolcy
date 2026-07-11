package com.modwodmm.bolcy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args){

        final ObjectMapper objectMapper = new ObjectMapper();
        final JsonHandler jsonHandler = new JsonHandler(objectMapper);
        final AuthHandler authHandler = new AuthHandler(jsonHandler);
        final ServerSockets serverSockets = new ServerSockets(objectMapper, authHandler);

        try{
            serverSockets.start();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
