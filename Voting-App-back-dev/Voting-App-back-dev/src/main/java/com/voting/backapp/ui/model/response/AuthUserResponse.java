package com.voting.backapp.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class AuthUserResponse implements Serializable {
    private static final long serialVersionUID = 8449394667369148477L;
    private String idUser;
    private String email;

    private String firstName;
    private String lastName;
    private String jwt;

    private Set<RoleResponse> roles;
}

