package com.modwodmm.bolcy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args){

        try(ServerSocket serverSocket = new ServerSocket(1001)){
            System.out.println("Server started!");
            Socket client = serverSocket.accept();
            System.out.println("Client connected!");
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String username = input.readLine();
            System.out.println("Hi! " + username);
            String message;
            while((message = input.readLine()) != null){
                System.out.println(username + ": " + message);
            }
        }
        catch(IOException e){
            throw new RuntimeException();
        }

    }
}
