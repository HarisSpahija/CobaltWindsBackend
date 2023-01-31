package com.harisspahija.cobaltwindsbackend.security;

import com.harisspahija.cobaltwindsbackend.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService udService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(udService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .authorizeHttpRequests()

                // Teams
                .requestMatchers(HttpMethod.POST, "/teams").hasAuthority("PLAYER")
                .requestMatchers(HttpMethod.POST,"/teams/**/join").hasAuthority("PLAYER")
                .requestMatchers(HttpMethod.PUT, "/teams/*").hasAnyAuthority("TEAM_CAPTAIN", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/teams/*").hasAnyAuthority("TEAM_CAPTAIN", "ADMIN")
                // Players
                .requestMatchers(HttpMethod.POST, "/players").hasAuthority("USER")
                .requestMatchers(HttpMethod.PUT, "/players/*").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/players/*").hasAnyAuthority("USER", "ADMIN")
                // Roles Admin
                .requestMatchers(HttpMethod.GET, "/roles/*").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/roles/*").hasAuthority("ADMIN")
                // Users Admin
                .requestMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/users/*").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/users/*").hasAuthority("ADMIN")
                // Users Me
                .requestMatchers(HttpMethod.GET, "/users/me").hasAuthority("USER")
                .requestMatchers(HttpMethod.PUT, "/users/me").hasAuthority("USER")
                // Create new User
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                // Auth
                .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                // Public routes
                .requestMatchers(HttpMethod.GET, "/players/*").permitAll()
                .requestMatchers(HttpMethod.GET, "/*").permitAll()

                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}

