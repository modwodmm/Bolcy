package com.modwodmm.bolcy;

import java.util.List;

public class AuthHandler {

    private final JsonHandler jsonHandler;

    public AuthHandler(JsonHandler jsonHandler){
        this.jsonHandler = jsonHandler;
    }

    public boolean authentication(User incomingUser){
        List<User> existingUsers = jsonHandler.load();
        for(User user : existingUsers){
            if(user.getUsername().equals(incomingUser.getUsername())){
                if(user.getPassword().equals(incomingUser.getPassword())){
                    return true;
                }
                else{
                    jsonHandler.save(incomingUser);
                    return true;
                }
            }
        }
        jsonHandler.save(incomingUser);
        return true;
    }

}
