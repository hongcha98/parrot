package io.github.hongcha98.parrot.core.error;

public class ParrotException extends RuntimeException {
    public ParrotException(String message) {
        super(message);
    }

    public ParrotException(String message, Throwable cause) {
        super(message, cause);
    }
}
