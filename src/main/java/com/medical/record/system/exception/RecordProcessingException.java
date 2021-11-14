package com.medical.record.system.exception;

/**
 * Custom exception class for Record processing errors
 */
public class RecordProcessingException extends RuntimeException{

    public RecordProcessingException(String message){
        super(message);
    }
}
