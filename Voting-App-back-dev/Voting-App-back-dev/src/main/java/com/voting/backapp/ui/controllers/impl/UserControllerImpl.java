package com.voting.backapp.ui.controllers.impl;

import com.voting.backapp.exceptions.VotingAppException;
import com.voting.backapp.service.UserService;
import com.voting.backapp.shared.constants.ApiConstants;
import com.voting.backapp.shared.constants.CommonConstants;
import com.voting.backapp.shared.dto.UserDto;
import com.voting.backapp.ui.controllers.UserController;
import com.voting.backapp.ui.model.request.UserDetailsRequestModel;
import com.voting.backapp.ui.model.response.UserRest;
import com.voting.backapp.ui.model.response.VotingAppResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiConstants.API_V1 + ApiConstants.USERS)
public class UserControllerImpl implements UserController {

        private final UserService userService;

        public UserControllerImpl(UserService userService) {
                this.userService = userService;
        }

        @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
        @Override
        public VotingAppResponse<UserRest> createUser(@RequestBody @Valid UserDetailsRequestModel userDetails)
                        throws VotingAppException {
                ModelMapper modelMapper = new ModelMapper();
                UserDto userCreatedDto = userService.createUser(modelMapper.map(userDetails, UserDto.class));
                return new VotingAppResponse<>(CommonConstants.SUCCESS,
                                CommonConstants.USER_CREATED,
                                HttpStatus.CREATED.toString(),
                                modelMapper.map(userCreatedDto, UserRest.class));
        }

        @GetMapping(path = "/{email}", produces = { MediaType.APPLICATION_JSON_VALUE })
        @Override
        public VotingAppResponse<UserRest> getUser(@PathVariable String email) throws VotingAppException {
                ModelMapper modelMapper = new ModelMapper();
                UserRest returnedValue = modelMapper.map(userService.getUser(email), UserRest.class);
                return new VotingAppResponse<>(CommonConstants.SUCCESS,
                                CommonConstants.USER_FOUND,
                                HttpStatus.OK.toString(),
                                returnedValue);
        }

        @PutMapping(path = "/{idUser}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
                        MediaType.APPLICATION_JSON_VALUE })
        @Override
        public VotingAppResponse<UserRest> updateUser(@PathVariable String idUser,
                        @RequestBody @Valid UserDetailsRequestModel userDetails) throws VotingAppException {
                ModelMapper modelMapper = new ModelMapper();
                UserDto userDtoToUpdate = modelMapper.map(userDetails, UserDto.class);
                UserDto userDtoUpdated = userService.updateUser(idUser, userDtoToUpdate);
                return new VotingAppResponse<>(CommonConstants.SUCCESS,
                                CommonConstants.USER_UPDATED,
                                HttpStatus.OK.toString(),
                                modelMapper.map(userDtoUpdated, UserRest.class));
        }

        @DeleteMapping(path = "/{idUser}", produces = { MediaType.APPLICATION_JSON_VALUE })
        @Override
        public VotingAppResponse<UserRest> deleteUser(@PathVariable String idUser) throws VotingAppException {
                userService.deleteUser(idUser);
                return new VotingAppResponse<>(CommonConstants.SUCCESS,
                                CommonConstants.USER_DELETED,
                                HttpStatus.OK.toString());
        }

}
