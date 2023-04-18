package com.voting.backapp.exceptions;

public class VotingAppException extends RuntimeException{

        private static final long serialVersionUID = 1L;

        public VotingAppException(String message) {
            super(message);
        }
}
