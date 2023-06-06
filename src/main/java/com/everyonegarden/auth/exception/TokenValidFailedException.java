package com.everyonegarden.auth.exception;


public class TokenValidFailedException extends IllegalStateException {

    public TokenValidFailedException() {
        super("Failed to generate Token.");
    }

    public TokenValidFailedException(String message) {
        super(message);
    }

}
