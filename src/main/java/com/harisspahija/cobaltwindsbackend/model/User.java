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

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "auth_roles")
    private Collection<AuthRole> authRoles;

    @OneToOne(fetch = FetchType.LAZY)
    @Column(name = "player_profile")
    private Player playerProfile;
}
