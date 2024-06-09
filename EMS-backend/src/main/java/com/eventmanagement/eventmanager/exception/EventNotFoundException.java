package com.eventmanagement.eventmanager.exception;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(String msg) {
        super(msg);
    }
}
