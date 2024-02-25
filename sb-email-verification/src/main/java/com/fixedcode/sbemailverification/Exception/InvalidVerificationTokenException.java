package com.fixedcode.sbemailverification.Exception;

public class InvalidVerificationTokenException extends RuntimeException{
    InvalidVerificationTokenException(String message)
    {
        super(message);
    }
}
