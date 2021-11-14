package com.medical.record.system.exception.handler;

import com.medical.record.system.exception.RecordProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles {@link RecordProcessingException}
     * @param exception
     * @param webRequest
     * @return
     */
    @ExceptionHandler(RecordProcessingException.class)
    public ResponseEntity<String> handleRecordProcessingException(RecordProcessingException exception, WebRequest webRequest){
        String errorMessage = "Unable to process the records. " + exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link RecordProcessingException}
     * @param exception
     * @param webRequest
     * @return
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception, WebRequest webRequest){
        String errorMessage = "The input is invalid. " + exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
