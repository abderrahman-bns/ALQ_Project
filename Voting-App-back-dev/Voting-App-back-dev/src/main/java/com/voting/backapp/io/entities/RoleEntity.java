package com.voting.backapp.io.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id_role")
})
@Getter
@Setter
public class RoleEntity extends AbstractEntity {
    private static final long serialVersionUID = 6831489103683760023L;
    @Column(name = "id_role", nullable = false, length = 10)
    private String idRole;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public RoleEntity(String idRole, ERole name) {
        this.name = name;
        this.idRole = idRole;
    }

    public RoleEntity() {
    }
}
