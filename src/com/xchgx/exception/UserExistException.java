package com.xchgx.exception;

@SuppressWarnings("serial")
public class UserExistException extends Exception
{
    public UserExistException(String errorMsg)
    {
        super(errorMsg);
    }
}
