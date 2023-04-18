package com.voting.backapp.ui.controllers;

import com.voting.backapp.exceptions.VotingAppException;
import com.voting.backapp.ui.model.request.LoginRequest;
import com.voting.backapp.ui.model.response.AuthUserResponse;
import com.voting.backapp.ui.model.response.VotingAppResponse;

public interface AuthController {
    VotingAppResponse<AuthUserResponse> authenticateUser(LoginRequest loginRequest) throws VotingAppException;
}
