package com.bbb.cityservice.exception;

public class CityAlreadyExistsException extends RuntimeException {
    public  CityAlreadyExistsException(String msg){
        super(msg);
    }
}
