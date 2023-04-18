package com.voting.backapp.ui.controllers;

import com.voting.backapp.exceptions.VotingAppException;
import com.voting.backapp.ui.model.request.UserDetailsRequestModel;
import com.voting.backapp.ui.model.response.UserRest;
import com.voting.backapp.ui.model.response.VotingAppResponse;


public interface UserController {

    VotingAppResponse<UserRest> createUser(UserDetailsRequestModel userDetails) throws VotingAppException;

    VotingAppResponse<UserRest> getUser(String email) throws VotingAppException;

    VotingAppResponse<UserRest> updateUser(String email, UserDetailsRequestModel userDetails) throws VotingAppException;

    VotingAppResponse<UserRest> deleteUser(String email) throws VotingAppException;


}
