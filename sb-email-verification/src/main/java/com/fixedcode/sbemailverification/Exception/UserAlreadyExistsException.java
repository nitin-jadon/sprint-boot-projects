package com.fixedcode.sbemailverification.Exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String exception)
    {
        super(exception);
    }
}
