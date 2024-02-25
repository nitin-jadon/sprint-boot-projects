package com.fixedcode.securerestapiilibrary.Exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String info)
    {
        super(info);
    }
}