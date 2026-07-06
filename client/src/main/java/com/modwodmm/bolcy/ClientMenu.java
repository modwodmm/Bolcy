package com.modwodmm.bolcy;

import java.util.Scanner;


public class ClientMenu {
    private final Scanner scanner;
    private final JsonHandler jsonHandler = new JsonHandler();

    public ClientMenu(Scanner scanner){
        this.scanner = scanner;
    }

    public void start(){
        System.out.println("*-*-*-Bolcy-*-*-*");
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        if(jsonHandler.hasAccount()){
            System.out.println("1. Connect\n2. Exit");
            int choice;
            try{
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            if(choice == 1){
                System.out.println("Umm...");
                try{
                    Thread.sleep(3000);
                }
                catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
                System.out.println("I haven't worked on that yet 😅");
            }
            else{
                System.exit(0);
            }
        }
        else{
            System.out.println("1. Register\n2. Exit");
            int choice;
            try{
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            if(choice == 1){
                System.out.println("Enter your username:");
                String username = scanner.nextLine();
                System.out.println("Enter your password:");
                String password = scanner.nextLine();
                User user = new User(username, password);
                jsonHandler.save(user);
                System.out.println("Account created successfully!");
            }
            else{
                System.exit(0);
            }
        }
    }
}
