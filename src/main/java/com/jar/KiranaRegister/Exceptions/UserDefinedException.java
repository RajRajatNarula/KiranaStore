package com.jar.KiranaRegister.Exceptions;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDefinedException extends Exception
{
    @Autowired
    public UserDefinedException(String str)
    {
        super(str);
    }
}
