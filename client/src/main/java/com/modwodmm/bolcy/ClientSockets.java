package com.modwodmm.bolcy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.Scanner;

public class ClientSockets {

    private Socket socket;
    private final ObjectMapper objectMapper;
    private PrintWriter writer;
    private boolean connected;
    private final Scanner scanner;
    private BufferedReader reader;
    private final ConfigManager configManager;

    public ClientSockets(ObjectMapper objectMapper, Scanner scanner, ConfigManager configManager){
        this.objectMapper = objectMapper;
        this.scanner = scanner;
        this.configManager = configManager;
    }

    //Handles interface and data sharing
    public void startChat(User user) throws IOException{
        System.out.println("Connecting...");
        connect(configManager.getHost(), configManager.getPort());
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Sending Data...");
        sendData(user);
        Thread sending = new Thread(() -> sendMessages());
        Thread receiving = new Thread(() -> receiveMessages());
        System.out.println("Start your conversation!(type /close to close the app)");
        sending.start();
        receiving.start();
    }

    //Handles connecting
    private void connect(String hostName, int port) throws IOException{
            socket = new Socket(hostName, port);
            connected = true;
    }

    //Sends data to the server
    private void sendData(User user){
        try{
            String userInfo = objectMapper.writeValueAsString(user);
            writer.println(userInfo);
        }
        catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    //Sends messages to the server
    private void sendMessages(){
        while(connected){
            String message = scanner.nextLine();
            if(message.equals("/close")){
                disconnect();
                System.exit(0);
            }
            writer.println(message);
        }
    }

    //Receives messages from others
    private void receiveMessages(){
        while(connected){
            try{
                String message = reader.readLine();
                if(message == null){
                    System.out.println("Server Disconnected");
                    disconnect();
                    System.exit(0);
                }
                System.out.println(message);
            }
            catch(IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    //Handles disconneting
    private void disconnect(){
        try {
            connected = false;
            if(reader != null) {
                reader.close();
            }
            if(writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
