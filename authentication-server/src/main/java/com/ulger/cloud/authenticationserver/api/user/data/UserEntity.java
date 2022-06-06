package com.ulger.cloud.authenticationserver.api.user.data;

import com.sun.istack.NotNull;
import com.ulger.usermanager.api.User;

import javax.persistence.*;

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

    public UserEntity() {
    }

    public UserEntity(@NotNull String email, @NotNull String displayName, @NotNull String pwdHash) {
        this.email = email;
        this.displayName = displayName;
        this.pwdHash = pwdHash;
    }

    public UserEntity(Long id, @NotNull String email, @NotNull String displayName, @NotNull String pwdHash) {
        this(email, displayName, pwdHash);
        this.id = id;
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
}