package dev.araz.exception;

public class NotTaskPriorityException extends RuntimeException {
    public NotTaskPriorityException(String message) {
        super(message);
    }
}