package com.voting.backapp.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DetailsValidationError {
    private String fieldName;
    private String defaultMessage;
}
