package com.voting.backapp.exceptions;

public class UserAsCandidateAndPostServiceException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UserAsCandidateAndPostServiceException(String message) {
        super(message);
    }
}
