package dev.araz.exception;

public class NotIssueTypeException extends RuntimeException {
    public NotIssueTypeException(String message) {
        super(message);
    }
}