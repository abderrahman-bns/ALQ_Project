package com.voting.backapp.ui.model.response;

import com.voting.backapp.io.entities.ERole;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoleResponse implements Serializable {
    private static final long serialVersionUID = 986690101595762533L;
    private String idRole;
    private ERole name;

}
