package edu.kriale.algorithms.sem5.task6.graph.exception;

public class NoSuchVertexException extends RuntimeException {
    public NoSuchVertexException() {
    }

    public NoSuchVertexException(String message) {
        super(message);
    }

    public NoSuchVertexException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchVertexException(Throwable cause) {
        super(cause);
    }

    public NoSuchVertexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
