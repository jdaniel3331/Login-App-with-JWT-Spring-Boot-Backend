package com.jdaniel.login_with_jwt_good.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Short roleId;
    @Column(nullable = false, unique = true, length = 20)
    private String name;

    // Tabla intermedia roles_permissions
    @ManyToMany(fetch = FetchType.LAZY) // No se cargan los permisos hasta que se llame al metogo getPermissions()
    @JoinTable(
            name = "roles_permissions",
            schema = "users",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    // Permisos de cada rol
    private Set<Permission> permissions = new HashSet<>();
}
