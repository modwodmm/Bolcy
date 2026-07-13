package com.modwodmm.bolcy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSockets {

    private final ObjectMapper objectMapper;
    private final AuthHandler authHandler;
    private List<ClientHandler> clients = new ArrayList<>();
    private final ConfigManager configManager;
    private final JsonHandler jsonHandler;

    public ServerSockets(ObjectMapper objectMapper, AuthHandler authHandler, ConfigManager configManager, JsonHandler jsonHandler){
        this.objectMapper = objectMapper;
        this.authHandler = authHandler;
        this.configManager = configManager;
        this.jsonHandler = jsonHandler;
    }

    //Handles starting and connecting
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(configManager.getPort());
        System.out.println("Server started!");
        while(true){
            Socket client = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(client, objectMapper, authHandler, this, jsonHandler);
            clients.add(clientHandler);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }

    }

    //Broadcasts messages to the users
    public void broadcast(String username, String message){
        for(ClientHandler client : clients){
            client.sendMessage(username + ": " + message);
        }
    }

    public void serverBroadcast(String message){
        for(ClientHandler client : clients){
            client.sendMessage("[Server] " + message);
        }
    }

}
