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
    private final JsonHandler jsonHandler;

    public ClientHandler(Socket client, ObjectMapper objectMapper, AuthHandler authHandler, ServerSockets serverSockets, JsonHandler jsonHandler) throws IOException {
        this.client = client;
        this.objectMapper = objectMapper;
        this.authHandler = authHandler;
        this.serverSockets = serverSockets;
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        writer = new PrintWriter(client.getOutputStream(), true);
        this.jsonHandler = jsonHandler;
    }

    @Override
    public void run(){
        try{
            authenticateUser();
            String message;
            while((message = reader.readLine()) != null){
                if(message.equals("/close")){
                    serverSockets.serverBroadcast(user.getUsername() + " has left the chat");
                    break;
                }
                serverSockets.broadcast(user.getUsername(), message);
            }
        }
        catch(IOException e){
            serverSockets.serverBroadcast(user.getUsername() + " disconnected!");
        }
        finally{
            disconnect();
        }
    }

    //Creates user and authenticates it
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

    //Handles disconnecting
    public void disconnect(){
        try{
            reader.close();
            client.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Sends messages to the related user
    public void sendMessage(String message){
        writer.println(message);
    }

}
