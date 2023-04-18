package com.voting.backapp.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SharedDto implements Serializable {
    private static final long serialVersionUID = -3025843444654471773L;
    private long id;
    private Date createdAt;
    private Date updatedAt;
}
