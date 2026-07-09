package com.modwodmm.bolcy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonHandler {
    private final ObjectMapper objectMapper;
    private final Path path = Path.of("data", "account.json");

    public JsonHandler(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public void save(User user){
        try{
            objectMapper.writeValue(path.toFile(), user);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public User load(){
        try{
            return objectMapper.readValue(path.toFile(), User.class);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public boolean hasAccount(){
        try{
            return Files.size(path) > 0;
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
