package com.laba.solvd.exceptions;

public class NoStudentsException extends Exception{
    public NoStudentsException(){};
    public NoStudentsException(String message) {
        super(message);
    }
}
