package com.voting.backapp.exceptions;

public class PostServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PostServiceException(String message) {
        super(message);
    }

}
