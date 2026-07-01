package com.modwodmm.bolcy;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args){



        try(Socket socket = new Socket("localhost", 1001)){
            System.out.println("Connected to server");
            while(true){
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                output.println(message);
            }
        }
        catch(IOException e){
            throw new RuntimeException();
        }

    }
}
