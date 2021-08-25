package dev.araz.exception;

public class NotProjectTypeException extends RuntimeException{
    public NotProjectTypeException(String message) {
        super(message);
    }

    public NotProjectTypeException() {
    }
}
