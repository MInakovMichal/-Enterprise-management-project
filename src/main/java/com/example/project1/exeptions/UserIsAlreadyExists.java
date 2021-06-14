package com.example.project1.exeptions;

public class UserIsAlreadyExists extends RuntimeException {

    public UserIsAlreadyExists(String message) {
        super(message);
    }
}
