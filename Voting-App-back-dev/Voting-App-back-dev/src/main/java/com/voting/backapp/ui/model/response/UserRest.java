package com.voting.backapp.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class UserRest implements Serializable {
    private static final long serialVersionUID = -3160350051252899687L;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Set<RoleResponse> roles;
}
