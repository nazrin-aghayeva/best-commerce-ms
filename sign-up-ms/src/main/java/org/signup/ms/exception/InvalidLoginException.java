package org.signup.ms.exception;

public class InvalidLoginException extends RuntimeException {

    public InvalidLoginException(String msg) {
        super(msg);
    }
}
