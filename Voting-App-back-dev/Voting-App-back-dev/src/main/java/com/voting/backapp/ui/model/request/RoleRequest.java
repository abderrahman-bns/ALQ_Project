package com.voting.backapp.ui.model.request;

import com.voting.backapp.io.entities.ERole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
    private ERole name;

    public RoleRequest(String name) {
        this.name = ERole.valueOf(name);
    }
}
