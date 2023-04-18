package com.voting.backapp.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto extends SharedDto {
    private static final long serialVersionUID = 1946264330749745174L;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private Set<RoleDto> roles;
}
