package com.harisspahija.cobaltwindsbackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Collection;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @SuppressWarnings("unused")
    private String id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "auth_roles")
    private Collection<AuthRole> authRoles;

    @OneToOne(fetch = FetchType.LAZY)
    private Player playerProfile;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<AuthRole> getAuthRoles() {
        return authRoles;
    }

    public void setAuthRoles(Collection<AuthRole> authRoles) {
        this.authRoles = authRoles;
    }

    public Player getPlayerProfile() {
        return playerProfile;
    }

    public void setPlayerProfile(Player playerProfile) {
        this.playerProfile = playerProfile;
    }
}
