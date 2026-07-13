package com.modwodmm.bolcy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {

    private final Path path = Path.of("data", "users.json");
    private final ObjectMapper objectMapper;

    public JsonHandler(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    //Handles saving user data
    public void save(User user) {
        try{
            List<User> users = load();
            users.add(user);
            objectMapper.writeValue(path.toFile(), users);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Handles loading user data
    public List<User> load(){
        try{
            if(Files.size(path) == 0){
                return new ArrayList<>();
            }
            return objectMapper.readValue(path.toFile(), new TypeReference<List<User>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
