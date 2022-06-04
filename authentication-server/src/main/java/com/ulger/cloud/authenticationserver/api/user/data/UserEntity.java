package com.ulger.cloud.authenticationserver.api.user.data;

import com.sun.istack.NotNull;
import com.ulger.usermanager.api.User;

import javax.persistence.*;

@Entity
@Table(name = "user", indexes = @Index(name = "idx_1_unq_email", unique = true, columnList = "email"))
public class UserEntity implements User {

    @Id
    @Column(name="id", unique = true, nullable = false, precision = 10)
    private String id;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="displayName", nullable = false)
    private String displayName;

    @Column(name="pwdHash", nullable = false)
    private String pwdHash;

    public UserEntity() {
    }

    public UserEntity(@NotNull String email, @NotNull String displayName, @NotNull String pwdHash) {
        this.email = email;
        this.displayName = displayName;
        this.pwdHash = pwdHash;
    }

    public UserEntity(String id, @NotNull String email, @NotNull String displayName, @NotNull String pwdHash) {
        this(email, displayName, pwdHash);
        this.id = id;
    }

    @Override
    public String getId() {
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