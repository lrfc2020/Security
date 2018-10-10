package com.local.security.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameNotfoundException extends AuthenticationException {
    public  UsernameNotfoundException(String  e){
        super(e);
    }
}
