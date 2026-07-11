package com.modwodmm.bolcy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private final Socket client;
    private User user;
    private final ObjectMapper objectMapper;
    private final BufferedReader reader;
    private final AuthHandler authHandler;
    private final ServerSockets serverSockets;
    private final PrintWriter writer;

    public ClientHandler(Socket client, ObjectMapper objectMapper, AuthHandler authHandler, ServerSockets serverSockets) throws IOException {
        this.client = client;
        this.objectMapper = objectMapper;
        this.authHandler = authHandler;
        this.serverSockets = serverSockets;
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        writer = new PrintWriter(client.getOutputStream());
    }

    @Override
    public void run(){
        try{
            authenticateUser();
            String message;
            while((message = reader.readLine()) != null){
                serverSockets.broadcast(user.getUsername(), message);
            }
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        finally{
            disconnect();
        }
    }

    public void authenticateUser(){
        try{
            String userInfo = reader.readLine();
            user = objectMapper.readValue(userInfo, User.class);
            authHandler.authentication(user);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect(){
        try{
            reader.close();
            client.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message){
        writer.println(message);
    }

}
