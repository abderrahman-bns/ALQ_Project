package com.voting.backapp.ui.model.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private String provider;
    private Date date;
    private String message;
    private String details;
}
