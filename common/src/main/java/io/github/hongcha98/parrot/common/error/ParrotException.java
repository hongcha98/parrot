package io.github.hongcha98.parrot.common.error;

public class ParrotException extends RuntimeException {

    public ParrotException(String message) {
        super(message);
    }

    public ParrotException(Throwable cause) {
        super(cause);
    }

    public ParrotException(String message, Throwable cause) {
        super(message, cause);
    }

}
