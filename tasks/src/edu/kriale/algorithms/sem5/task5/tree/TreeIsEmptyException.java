package edu.kriale.algorithms.sem5.task5.tree;

public class TreeIsEmptyException extends RuntimeException {
    public TreeIsEmptyException() {
    }

    public TreeIsEmptyException(String message) {
        super(message);
    }

    public TreeIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public TreeIsEmptyException(Throwable cause) {
        super(cause);
    }

    public TreeIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
