package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.dto.UserDto;
import com.harisspahija.cobaltwindsbackend.dto.UserInputDto;
import com.harisspahija.cobaltwindsbackend.exception.RepositoryNoRecordException;
import com.harisspahija.cobaltwindsbackend.model.User;
import com.harisspahija.cobaltwindsbackend.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserDto getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RepositoryNoRecordException(id));

        return transferToDto(user);
    }

    public UserDto createUser(UserInputDto dto) {
        userRepository.findUserByEmail(dto.getEmail()).orElseThrow(() -> new DataIntegrityViolationException("Email is already used. Try restoring your account or a different email."));

        User user = new User();

        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));

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
}
