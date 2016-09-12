package com.antonsyzko.recruiting.exception;

/**
 * @author ihor zadyra
 */

public class SingleResultNotFoundException extends Exception {

    public SingleResultNotFoundException(String message) {
        super(message);
    }

    public SingleResultNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
