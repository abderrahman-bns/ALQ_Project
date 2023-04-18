package com.voting.backapp.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.voting.backapp.ui.model.response.DetailsValidationError;
import com.voting.backapp.ui.model.response.ExceptionResponse;
import com.voting.backapp.ui.model.response.ExceptionResponseValidation;

@ControllerAdvice
@RestController
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "By GeneraleException", new Date(), e.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { UserServiceException.class })
    public ResponseEntity<Object> handleUserServiceException(@NotNull UserServiceException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getLocalizedMessage(), new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { PostServiceException.class })
    public ResponseEntity<Object> handlePostServiceException(@NotNull PostServiceException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getLocalizedMessage(), new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { UserAsCandidateAndPostServiceException.class })
    public ResponseEntity<Object> handleUserAsCandidateAndPostServiceException(
            @NotNull UserAsCandidateAndPostServiceException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getLocalizedMessage(), new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { VotingAppException.class })
    public ResponseEntity<Object> handleVotingAppException(@NotNull VotingAppException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getLocalizedMessage(), new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {
        List<DetailsValidationError> details = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(field -> details.add(new DetailsValidationError(field.getField(), field.getDefaultMessage())));
        ExceptionResponseValidation exceptionResponseValidation = new ExceptionResponseValidation(
                "By NotValidException", new Date(), "Validations fields", details);

        return new ResponseEntity<>(exceptionResponseValidation, HttpStatus.BAD_REQUEST);
    }
}
