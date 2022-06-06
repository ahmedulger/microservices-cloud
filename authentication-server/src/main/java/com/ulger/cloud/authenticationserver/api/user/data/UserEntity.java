package com.ulger.cloud.authenticationserver.api.user.data;

import com.ulger.usermanager.api.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users", indexes = @Index(name = "idx_1_unq_email", unique = true, columnList = "email"))
public class UserEntity implements User {

    @Id
    @GeneratedValue(generator = "seq_gen_user", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_gen_user", sequenceName = "seq_gen_user", allocationSize = 1)
    @Column(name="id", unique = true, nullable = false, precision = 10)
    private Long id;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="displayName", nullable = false)
    private String displayName;

    @Column(name="pwd_hash", nullable = false)
    private String pwdHash;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    public UserEntity() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getCredential() {
        return pwdHash;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }
}