package com.harisspahija.cobaltwindsbackend.repository;

import com.harisspahija.cobaltwindsbackend.model.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepository extends JpaRepository<AuthRole, String> {
}
