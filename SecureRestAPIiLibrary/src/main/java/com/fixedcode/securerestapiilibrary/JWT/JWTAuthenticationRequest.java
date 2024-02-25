package com.fixedcode.securerestapiilibrary.JWT;

import lombok.Data;

@Data
public class JWTAuthenticationRequest {
    private String userName;
    private String password;
    //name should be same as written here for the headers in postman
}
