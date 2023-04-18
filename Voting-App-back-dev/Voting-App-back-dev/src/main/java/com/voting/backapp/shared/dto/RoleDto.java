package com.voting.backapp.shared.dto;

import com.voting.backapp.io.entities.ERole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto extends SharedDto {

    private static final long serialVersionUID = 7569970545467852559L;
    private String idRole;
    private ERole name;
}
