package com.modwodmm.bolcy;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        try(Socket socket = new Socket("localhost", 1001)){
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to server");
            System.out.println("Enter a username:");
            String username = scanner.nextLine();
            output.println(username);
            while(true){
                String message = scanner.nextLine();
                output.println(message);
            }
        }
        catch(IOException e){
            throw new RuntimeException();
        }

    }
}
