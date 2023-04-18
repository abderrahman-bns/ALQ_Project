package com.voting.backapp.ui.controllers.impl;

import com.voting.backapp.exceptions.VotingAppException;
import com.voting.backapp.service.AuthService;
import com.voting.backapp.shared.constants.ApiConstants;
import com.voting.backapp.shared.constants.CommonConstants;
import com.voting.backapp.shared.dto.AuthUserDto;
import com.voting.backapp.ui.controllers.AuthController;
import com.voting.backapp.ui.model.request.LoginRequest;
import com.voting.backapp.ui.model.response.AuthUserResponse;
import com.voting.backapp.ui.model.response.VotingAppResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiConstants.API_V1 + ApiConstants.AUTH)
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public VotingAppResponse<AuthUserResponse> authenticateUser(@RequestBody @Valid LoginRequest loginRequest)
            throws VotingAppException {
        ModelMapper modelMapper = new ModelMapper();
        AuthUserDto authUserDtoToAuthenticate = modelMapper.map(loginRequest, AuthUserDto.class);
        AuthUserResponse returnedValue = modelMapper.map(authService.authenticateUser(authUserDtoToAuthenticate),
                AuthUserResponse.class);
        return new VotingAppResponse<>(CommonConstants.SUCCESS,
                CommonConstants.USER_AUTHENTICATED,
                HttpStatus.ACCEPTED.toString(),
                returnedValue);
    }

}
