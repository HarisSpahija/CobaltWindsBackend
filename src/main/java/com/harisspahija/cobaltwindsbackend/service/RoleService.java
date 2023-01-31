package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.exception.BadRequestCustomException;
import com.harisspahija.cobaltwindsbackend.exception.RepositoryNoRecordException;
import com.harisspahija.cobaltwindsbackend.model.AuthRole;
import com.harisspahija.cobaltwindsbackend.model.User;
import com.harisspahija.cobaltwindsbackend.repository.AuthRoleRepository;
import com.harisspahija.cobaltwindsbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final UserRepository userRepository;
    private final AuthRoleRepository authRoleRepository;

    public RoleService(UserRepository userRepository, AuthRoleRepository authRoleRepository) {
        this.userRepository = userRepository;
        this.authRoleRepository = authRoleRepository;
    }

    public void addAuthRolesToUser(String userId, List<String> authRoles) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RepositoryNoRecordException(userId));
        Collection<AuthRole> authRoleObjects = new ArrayList<>();

        for (String authRole : authRoles) {
            Optional<AuthRole> foundRole = authRoleRepository.findAuthRoleByAuthRoleName(authRole);
            if (foundRole.isEmpty()) {
                throw new BadRequestCustomException("Role " + authRole + " does not exist in the system.");
            }
            authRoleObjects.add(foundRole.get());
        }

        Collection<AuthRole> existingAuthRoles = user.getAuthRoles();
        existingAuthRoles.addAll(authRoleObjects);
        user.setAuthRoles(existingAuthRoles);
        userRepository.save(user);
    }

    public Collection<AuthRole> getAllAuthRoles() {
       return authRoleRepository.findAll();
    }
}
