package dev.araz.exception;

public class UserNotExist extends RuntimeException {
    public UserNotExist() {
    }
    public UserNotExist(String reason) {
        super(reason);
    }
}