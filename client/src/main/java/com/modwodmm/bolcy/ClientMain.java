package com.modwodmm.bolcy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args){

        //Creates necessary objects

        final Scanner scanner = new Scanner(System.in);
        final ObjectMapper objectMapper = new ObjectMapper();
        final JsonHandler jsonHandler = new JsonHandler(objectMapper);
        final PasswordHashing passwordHashing = new PasswordHashing();
        final ConfigManager configManager = new ConfigManager();
        final ClientSockets clientSockets = new ClientSockets(objectMapper, scanner, configManager);
        final ClientMenu clientMenu = new ClientMenu(scanner, jsonHandler, passwordHashing, clientSockets);

        //Starts the client-side app
        try{
            clientMenu.start();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }

    }
}
