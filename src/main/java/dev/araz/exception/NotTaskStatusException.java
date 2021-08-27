package dev.araz.exception;

public class NotTaskStatusException extends RuntimeException {
    public NotTaskStatusException(String message) {
        super(message);
    }
}