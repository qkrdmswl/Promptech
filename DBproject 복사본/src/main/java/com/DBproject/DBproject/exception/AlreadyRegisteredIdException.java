package com.DBproject.DBproject.exception;

public class AlreadyRegisteredIdException extends CustomException{
    private static final long serialVersionUID = -7799696001358188839L;
    public AlreadyRegisteredIdException(String message) {

            super(message);
        }
}
