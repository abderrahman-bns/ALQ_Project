package com.voting.backapp.service;

import com.voting.backapp.shared.dto.AuthUserDto;

public interface AuthService {
    AuthUserDto authenticateUser(AuthUserDto authUserDto);
}
