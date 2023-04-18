package com.voting.backapp.service.impl;

import com.voting.backapp.exceptions.UserServiceException;
import com.voting.backapp.io.entities.ERole;
import com.voting.backapp.io.entities.RoleEntity;
import com.voting.backapp.io.entities.UserEntity;
import com.voting.backapp.io.repositories.RoleRepository;
import com.voting.backapp.io.repositories.UserRepository;
import com.voting.backapp.security.service.UserDetailsImpl;
import com.voting.backapp.service.UserService;
import com.voting.backapp.shared.constants.ExceptionConstant;
import com.voting.backapp.shared.dto.RoleDto;
import com.voting.backapp.shared.dto.UserDto;
import com.voting.backapp.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Utils utils;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, Utils utils,
            PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.utils = utils;
        this.encoder = encoder;
    }

    @Override
    public UserDto createUser(UserDto user) {
        logger.info("Creating user");
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new UserServiceException(ExceptionConstant.USER_ALREADY_EXIST);
        });
        ModelMapper modelMapper = new ModelMapper();
        user.setUserId(utils.generateUserId(30));
        user.setEncryptedPassword(encoder.encode(user.getPassword()));
        Set<RoleDto> rolesDto = user.getRoles();
        Set<RoleEntity> rolesEntities = new HashSet<>();
        if (rolesDto == null || rolesDto.isEmpty()) {
            RoleEntity role = roleRepository.findByName(ERole.ROLE_USER).orElse(
                    roleRepository.save(new RoleEntity(utils.generateRoleId(10), ERole.ROLE_USER)));
            rolesEntities.add(role);
        } else {
            for (RoleDto role : rolesDto) {
                switch (role.getName()) {
                    case ROLE_ADMIN:
                        RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElse(
                                roleRepository.save(new RoleEntity(utils.generateRoleId(10), ERole.ROLE_ADMIN)));
                        rolesEntities.add(adminRole);
                        break;
                    case ROLE_CONDIDATE:
                        RoleEntity condidateRole = roleRepository.findByName(ERole.ROLE_CONDIDATE).orElse(
                                roleRepository.save(new RoleEntity(utils.generateRoleId(10), ERole.ROLE_CONDIDATE)));
                        rolesEntities.add(condidateRole);
                        break;
                    default:
                        RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER).orElse(
                                roleRepository.save(new RoleEntity(utils.generateRoleId(10), ERole.ROLE_USER)));
                        rolesEntities.add(userRole);
                }
            }
        }
        UserEntity userEntityToSave = modelMapper.map(user, UserEntity.class);
        userEntityToSave.setRoles(rolesEntities);
        return modelMapper.map(userRepository.save(userEntityToSave), UserDto.class);
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userFounded = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserServiceException(ExceptionConstant.USER_NOT_FOUND));
        return new ModelMapper().map(userFounded, UserDto.class);
    }

    @Override
    public UserDto updateUser(String idUser, UserDto user) {
        UserEntity userFounded = userRepository.findByUserId(idUser)
                .orElseThrow(() -> new UserServiceException(ExceptionConstant.USER_NOT_FOUND));
        userFounded.setFirstName(user.getFirstName());
        userFounded.setLastName(user.getLastName());
        userFounded.setEmail(user.getEmail());
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRepository.save(userFounded), UserDto.class);
    }

    @Override
    public void deleteUser(String idUser) {
        UserEntity userFounded = userRepository.findByUserId(idUser)
                .orElseThrow(() -> new UserServiceException(ExceptionConstant.USER_NOT_FOUND));
        try {
            userRepository.delete(userFounded);
        } catch (Exception e) {
            throw new UserServiceException(ExceptionConstant.ERROR_DELETING_USER + e.getMessage());
        }

    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ExceptionConstant.USER_NOT_FOUND));
        return UserDetailsImpl.build(user);
    }
}
