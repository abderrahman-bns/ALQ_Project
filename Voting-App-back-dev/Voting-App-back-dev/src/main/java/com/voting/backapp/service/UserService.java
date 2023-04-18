package com.voting.backapp.service;
import com.voting.backapp.shared.dto.UserDto;


public interface UserService {

    UserDto createUser(UserDto user);
    UserDto getUser(String email);
    UserDto updateUser(String idUser, UserDto user);
    void deleteUser(String idUser);

}
