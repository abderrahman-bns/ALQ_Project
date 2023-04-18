package com.voting.backapp.service.impl;

import com.voting.backapp.io.entities.ERole;
import com.voting.backapp.io.repositories.RoleRepository;
import com.voting.backapp.security.jwt.JwtUtils;
import com.voting.backapp.security.service.UserDetailsImpl;
import com.voting.backapp.service.AuthService;
import com.voting.backapp.shared.dto.AuthUserDto;
import com.voting.backapp.shared.dto.RoleDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private final RoleRepository roleRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.roleRepository = roleRepository;
    }

    @Override
    public AuthUserDto authenticateUser(AuthUserDto authUserDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authUserDto.getEmail(), authUserDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Set<RoleDto> roles = userDetails.getAuthorities().stream()
                .map(item -> {
                    RoleDto roleDto = new RoleDto();
                    roleDto.setName(ERole.valueOf(item.getAuthority()));
                    String idRole = roleRepository.findByName(ERole.valueOf(item.getAuthority())).isPresent() ?
                            roleRepository.findByName(ERole.valueOf(item.getAuthority())).get().getIdRole() :
                            null;
                    roleDto.setIdRole(idRole);
                    return roleDto;
                })
                .collect(Collectors.toSet());


        AuthUserDto authUser = new AuthUserDto();
        authUser.setIdUser(userDetails.getIdUser());
        authUser.setEmail(userDetails.getEmail());
        authUser.setFirstName(userDetails.getFirstName());
        authUser.setLastName(userDetails.getLastName());
        authUser.setJwt(jwt);
        authUser.setRoles(roles);
        return authUser;
    }
}
