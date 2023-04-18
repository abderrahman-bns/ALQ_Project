package com.voting.backapp.ui.model.response;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponseValidation {
    private String provider;
    private Date timestamp;
    private String message;
    private List<DetailsValidationError> details;
}
