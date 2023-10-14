package com.seen.foundlunteerjava.error;

import com.seen.foundlunteerjava.dto.response.MessageResponse;
import com.seen.foundlunteerjava.error.exception.DataNotFoundException;
import com.seen.foundlunteerjava.error.exception.BadPayloadException;
import com.seen.foundlunteerjava.error.exception.UnknownException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    MessageResponse validationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return MessageResponse.builder().message(errorMessage).build();
    }

    @ExceptionHandler(BadPayloadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    MessageResponse BadPayloadException(BadPayloadException e){
        return MessageResponse.builder().message(e.getMessage()).build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    MessageResponse unknownPropertiesJson(HttpMessageNotReadableException e){
        return MessageResponse.builder().message("Unknown property Name").build();
    }

    @ExceptionHandler(UnknownException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    MessageResponse unknownException(UnknownException e){
        return MessageResponse.builder().message("Something Wrong Contact Developer").build();
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    MessageResponse dataNotFoundException(DataNotFoundException e){
        return MessageResponse.builder().message(e.getMessage()).build();
    }
}
