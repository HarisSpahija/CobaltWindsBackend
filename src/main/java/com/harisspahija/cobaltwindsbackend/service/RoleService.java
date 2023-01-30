package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.exception.BadRequestCustomException;
import com.harisspahija.cobaltwindsbackend.exception.RepositoryNoRecordException;
import com.harisspahija.cobaltwindsbackend.model.AuthRole;
import com.harisspahija.cobaltwindsbackend.model.User;
import com.harisspahija.cobaltwindsbackend.repository.AuthRoleRepository;
import com.harisspahija.cobaltwindsbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {
    private final UserRepository userRepository;
    private final AuthRoleRepository authRoleRepository;

    public RoleService(UserRepository userRepository, AuthRoleRepository authRoleRepository) {
        this.userRepository = userRepository;
        this.authRoleRepository = authRoleRepository;
    }

    public void grantAuthRole(String userId, Collection<AuthRole> authRoles) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RepositoryNoRecordException(userId));

        for (AuthRole authRole : authRoles) {
            if (!roleExists(authRole)) {
                throw new BadRequestCustomException("Role " + authRole + " does not exist in the system.");
            }
        }

        user.setAuthRoles(authRoles);
        userRepository.save(user);
    }

    public boolean roleExists(AuthRole authRole) {
        return authRoleRepository.findAuthRoleByAuthRoleName(authRole.getAuthRoleName()).isPresent();
    }

    public Collection<AuthRole> getAllAuthRoles() {
       return authRoleRepository.findAll();
    }
}
