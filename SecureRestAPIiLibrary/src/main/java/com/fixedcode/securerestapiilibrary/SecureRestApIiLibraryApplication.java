package com.fixedcode.securerestapiilibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecureRestApIiLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecureRestApIiLibraryApplication.class, args);
    }
//securing books api requests depending on user authority role
}
