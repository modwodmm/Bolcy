package com.modwodmm.bolcy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerSockets {

    private final ObjectMapper objectMapper;
    private final AuthHandler authHandler;
    private List<ClientHandler> clients;

    public ServerSockets(ObjectMapper objectMapper, AuthHandler authHandler){
        this.objectMapper = objectMapper;
        this.authHandler = authHandler;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1001);
        while(true){
            Socket client = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(client, objectMapper, authHandler, this);
            clients.add(clientHandler);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }

    }

    public void broadcast(String username, String message){
        for(ClientHandler client : clients){
            client.sendMessage(username + ": " + message);
        }
    }

}
