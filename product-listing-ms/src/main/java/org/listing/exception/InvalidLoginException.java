package org.listing.exception;

public class InvalidLoginException extends RuntimeException {

    public InvalidLoginException(String msg) {
        super(msg);
    }
}
