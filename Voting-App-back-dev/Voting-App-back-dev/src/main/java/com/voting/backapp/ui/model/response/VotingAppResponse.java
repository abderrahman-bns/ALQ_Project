package com.voting.backapp.ui.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class VotingAppResponse <T> implements Serializable {

    private static final long serialVersionUID = 4944179481852829005L;
    private String status;
    private String message;
    private String code;
    private Date timestamp = new Date();
    private T data;

    public VotingAppResponse(String status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public VotingAppResponse(String status, String message, String code, T data) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public VotingAppResponse(String status, String message, String code, Date timestamp, T data) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
        this.data = data;
    }
}
