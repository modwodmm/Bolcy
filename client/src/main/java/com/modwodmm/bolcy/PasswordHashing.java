package com.modwodmm.bolcy;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class PasswordHashing {

    private final Argon2PasswordEncoder hasher = new Argon2PasswordEncoder(16, 32, 1, 60000, 10);

    /* Hashes password
    (Salt and other data is embedded into the hash)
     */
    public String hash(String password){
        return hasher.encode(password);
    }

}
