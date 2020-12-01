package ng.swift.Swift.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorControllerAdvice {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MessageSource messageSource;
    private ObjectError allError;

    public ErrorControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response<String>> handle(NotFoundException e) {
        Response<String> response = new Response<String>();
        response.setCode(404);
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response<String>> handle(DataIntegrityViolationException e) {
        Response<String> response = new Response<String>();
        response.setCode(400);
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handle(IllegalArgumentException e) {
        logger.error(e.getMessage(), e);

        Response<String> apiResponse = new Response<String>();
        if (e.getCause() != null) {
            apiResponse.setMessage(e.getCause().getMessage());
        } else {
            apiResponse.setMessage(e.getMessage());
        }
        apiResponse.setCode(400);
        apiResponse.setMessage(messageSource.getMessage(apiResponse.getMessage(), null, apiResponse.getMessage(), LocaleContextHolder.getLocale()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getPropertyPath() + " : " + violation.getMessage());
        }
        Response<List<String>> apiResponse = new Response<List<String>>();
        apiResponse.setCode(400);
        apiResponse.setMessage(errors.size() > 0 ? errors.get(0) : "Invalid request");
        apiResponse.setData(errors);
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
