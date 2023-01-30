package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.dto.UserDto;
import com.harisspahija.cobaltwindsbackend.dto.UserInputDto;
import com.harisspahija.cobaltwindsbackend.exception.RepositoryNoRecordException;
import com.harisspahija.cobaltwindsbackend.model.AuthRole;
import com.harisspahija.cobaltwindsbackend.model.User;
import com.harisspahija.cobaltwindsbackend.repository.AuthRoleRepository;
import com.harisspahija.cobaltwindsbackend.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final AuthRoleRepository authRoleRepository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, AuthRoleRepository authRoleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authRoleRepository = authRoleRepository;
        this.encoder = encoder;
    }

    public UserDto getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RepositoryNoRecordException(id));

        return transferToDto(user);
    }

    public UserDto createUser(UserInputDto dto) {
        Optional<User> existingUser = userRepository.findUserByEmail(dto.getEmail());
        if (existingUser.isPresent()) {
            throw new DataIntegrityViolationException("Email is already used. Try restoring your account or a different email.");
        }

        User user = new User();
        Collection<AuthRole> authRoles = new ArrayList<>();
        Optional<AuthRole> userAuthRole = authRoleRepository.findAuthRoleByAuthRoleName("USER");

        userAuthRole.ifPresent(authRoles::add);

        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setAuthRoles(authRoles);

        userRepository.save(user);

        return transferToDto(user);
    }

    private UserDto transferToDto(User user) {
        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setAuthRoles(user.getAuthRoles());
        dto.setPlayerProfile(user.getPlayerProfile());

        return dto;
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new RepositoryNoRecordException(username));
        return transferToDto(user);
    }
}
