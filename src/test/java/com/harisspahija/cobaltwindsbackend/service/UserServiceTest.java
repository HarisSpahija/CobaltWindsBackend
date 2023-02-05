package com.harisspahija.cobaltwindsbackend.service;

import com.harisspahija.cobaltwindsbackend.dto.UserDto;
import com.harisspahija.cobaltwindsbackend.dto.UserInputDto;
import com.harisspahija.cobaltwindsbackend.model.AuthRole;
import com.harisspahija.cobaltwindsbackend.model.Player;
import com.harisspahija.cobaltwindsbackend.model.User;
import com.harisspahija.cobaltwindsbackend.repository.AuthRoleRepository;
import com.harisspahija.cobaltwindsbackend.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthRoleRepository authRoleRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserService userService;

    @Before("")
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUser() {
        User user = new User();
        user.setId("123");
        user.setEmail("test@test.com");

        // mock the behavior of the userRepository to return the user object when findById method is called
        Mockito.when(userRepository.findById("123")).thenReturn(Optional.of(user));
        UserDto userDto = userService.getUser("123");

        assertNotNull(userDto);
        assertEquals("123", userDto.getId());
        assertEquals("test@test.com", userDto.getEmail());
    }

    @Test
    public void createUserButUserEmailAlreadyExists() {
        UserInputDto userInputDto = new UserInputDto();
        userInputDto.setEmail("test@cwl.com");
        userInputDto.setPassword("Welkom0123!@#");

        Optional<User> existingUser = Optional.of(new User());
        when(userRepository.findUserByEmail(userInputDto.getEmail())).thenReturn(existingUser);

        try {
            userService.createUser(userInputDto);
            fail("DataIntegrityViolationException was expected");
        } catch (DataIntegrityViolationException e) {
            assertEquals("Email is already used. Try restoring your account or a different email.", e.getMessage());
        }
    }

    @Test
    void createUser() {
        UserInputDto dto = new UserInputDto();
        dto.setEmail("test@test.com");
        dto.setPassword("password");

        AuthRole authRole = new AuthRole();
        authRole.setAuthRoleName("USER");
        Optional<AuthRole> userAuthRole = Optional.of(authRole);

        when(authRoleRepository.findAuthRoleByAuthRoleName("USER")).thenReturn(userAuthRole);
        when(encoder.encode("password")).thenReturn("encoded_password");
        when(userRepository.findUserByEmail("test@test.com")).thenReturn(Optional.empty());

        UserDto createdUser = userService.createUser(dto);

        assertNotNull(createdUser);
        assertEquals(createdUser.getEmail(), "test@test.com");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void transferToDto() {
        User user = new User();
        user.setId("testid");
        user.setEmail("email@example.com");
        user.setAuthRoles(Collections.emptyList());
        user.setPlayerProfile(null);

        UserDto dto = userService.transferToDto(user);

        assertEquals("testid", dto.getId());
        assertEquals("email@example.com", dto.getEmail());
        assertEquals(Collections.emptyList(), dto.getAuthRoles());
        assertNull(dto.getPlayerProfile());
    }

    @Test
    void getUserByUsername() {
        String username = "test@email.com";
        User user = new User();
        user.setId("testid");
        user.setEmail(username);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findUserByEmail(username)).thenReturn(userOptional);
        UserDto result = userService.getUserByUsername(username);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    void getPlayerIdByUsername() {
        String username = "test@example.com";
        User user = new User();
        user.setEmail(username);

        Player player = new Player();
        String playerId = "testid";
        player.setId(playerId);
        user.setPlayerProfile(player);

        when(userRepository.findUserByEmail(username)).thenReturn(Optional.of(user));

        String result = userService.getPlayerIdByUsername(username);

        assertEquals(result, playerId);
    }

    @Test
    void getPlayerIdByUsernameNoPlayer() {
        String username = "test@example.com";
        User user = new User();
        user.setEmail(username);
        when(userRepository.findUserByEmail(username)).thenReturn(Optional.of(user));
        assertNull(userService.getPlayerIdByUsername("test@example.com"));
    }

    @Test
    void addPlayerToUsername() {
        String username = "test@example.com";
        User user = new User();
        user.setEmail(username);
        Player player = new Player();
        player.setName("PlayerName");
        Collection<AuthRole> authRoles = new ArrayList<>();
        AuthRole authRole = new AuthRole();
        authRole.setAuthRoleName("USER");
        authRoles.add(authRole);
        when(authRoleRepository.findAuthRoleByAuthRoleName("PLAYER")).thenReturn(Optional.of(authRole));
        when(userRepository.findUserByEmail(username)).thenReturn(Optional.of(user));
        user.setAuthRoles(authRoles);
        userService.addPlayerToUsername(player, username);
        verify(userRepository, times(2)).save(user);
        UserDto result = userService.getUserByUsername(username);
        Assert.assertEquals(result.getAuthRoles(), user.getAuthRoles());
    }

    @Test
    void addRoleToUsername() {
        String username = "test@example.com";
        User user = new User();
        user.setEmail(username);
        Collection<AuthRole> authRoles = new ArrayList<>();
        AuthRole authRole = new AuthRole();
        authRole.setAuthRoleName("USER");
        authRoles.add(authRole);
        when(authRoleRepository.findAuthRoleByAuthRoleName("PLAYER")).thenReturn(Optional.of(authRole));
        when(userRepository.findUserByEmail(username)).thenReturn(Optional.of(user));
        user.setAuthRoles(authRoles);
        userService.addRoleToUsername(username, "PLAYER");
        verify(userRepository, times(1)).save(user);
        UserDto result = userService.getUserByUsername(username);
        Assert.assertEquals(result.getAuthRoles(), user.getAuthRoles());
    }

    @Test
    void addRoleToUserByPlayerId() {
        String userId = "test123";
        User user = new User();
        user.setId(userId);
        String playerId = "testPlayer123";
        Player player = new Player();
        player.setId(playerId);
        user.setPlayerProfile(player);
        Collection<AuthRole> authRoles = new ArrayList<>();
        AuthRole authRole = new AuthRole();
        authRole.setAuthRoleName("USER");
        authRoles.add(authRole);
        when(authRoleRepository.findAuthRoleByAuthRoleName("PLAYER")).thenReturn(Optional.of(authRole));
        when(userRepository.findUserByPlayerProfileId(playerId)).thenReturn(Optional.of(user));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        user.setAuthRoles(authRoles);
        userService.addRoleToUserByPlayerId(playerId, "PLAYER");
        verify(userRepository, times(1)).save(user);
        UserDto result = userService.getUser(userId);
        Assert.assertEquals(result.getAuthRoles(), user.getAuthRoles());
    }

    @Test
    void removeRoleFromUserByPlayerId() {
        String userId = "test123";
        User user = new User();
        user.setId(userId);
        String playerId = "testPlayer123";
        Player player = new Player();
        player.setId(playerId);
        user.setPlayerProfile(player);
        Collection<AuthRole> authRoles = new ArrayList<>();
        AuthRole authRole = new AuthRole();
        authRole.setAuthRoleName("USER");
        authRoles.add(authRole);
        when(authRoleRepository.findAuthRoleByAuthRoleName("ADMIN")).thenReturn(Optional.of(authRole));
        when(userRepository.findUserByPlayerProfileId(playerId)).thenReturn(Optional.of(user));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        user.setAuthRoles(authRoles);
        userService.removeRoleFromUserByPlayerId(playerId, "ADMIN");
        UserDto result = userService.getUser(userId);
        Assert.assertEquals(result.getAuthRoles(), user.getAuthRoles());
    }
}