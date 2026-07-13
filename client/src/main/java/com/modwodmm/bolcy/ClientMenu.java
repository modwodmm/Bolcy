package com.modwodmm.bolcy;

import java.io.IOException;
import java.util.Scanner;


public class ClientMenu {

    private final Scanner scanner;
    private final JsonHandler jsonHandler;
    private final PasswordHashing passwordHashing;
    private final ClientSockets clientSockets;

    public ClientMenu(Scanner scanner, JsonHandler jsonHandler, PasswordHashing passwordHashing, ClientSockets clientSockets){
        this.scanner = scanner;
        this.jsonHandler = jsonHandler;
        this.passwordHashing = passwordHashing;
        this.clientSockets = clientSockets;
    }

    //Start of the menu
    public void start() throws IOException{
        System.out.println("*-*-*-Bolcy-*-*-*");
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        if(jsonHandler.hasAccount()){
            connect();
        }
        else{
            register();
        }
    }

    //Handles connecting interface
    private void connect() throws IOException {
        System.out.println("1. Connect\n2. Exit");
        int choice = getChoice();
        boolean correctChoice = false;
        while(!correctChoice){
            switch(choice){
                case 1:
                    User user = jsonHandler.load();
                    clientSockets.startChat(user);
                    correctChoice = true;
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Enter a correct choice please!");
                    choice = getChoice();
            }
        }
    }

    //Handles registering interface
    private void register(){
        System.out.println("1. Register\n2. Exit");
        int choice = getChoice();
        if(choice == 1){
            System.out.println("Enter your username:");
            String username = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            String passwordHash = passwordHashing.hash(password);
            User user = new User(username, passwordHash);
            jsonHandler.save(user);
            System.out.println("Account created successfully!");
            try{
                connect();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.exit(0);
        }
    }

    //Helper method to get the choice from the user
    private int getChoice(){
        while(true){
            try{
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a correct choice!");
            }
        }
    }

}
