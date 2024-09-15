package org.denis.coinkeeper.api.controllers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.denis.coinkeeper.api.entities.UserEntity;
import org.denis.coinkeeper.api.factories.UserDtoFactory;
import org.denis.coinkeeper.api.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RequiredArgsConstructor
@Transactional
@RestController
public class UserController {


    private final PasswordEncoder passwordEncoder;
    private final UserDtoFactory userDtoFactory;
    private final UserRepository userRepository;

    public static final String REGISTER_PAGE = "/api/main/register";

    @PostMapping(REGISTER_PAGE)
    public ResponseEntity<UserEntity> register(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            UriComponentsBuilder uriComponentsBuilder) {
        UserEntity myUserEntity = UserEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        userRepository.save(myUserEntity);

        return ResponseEntity
                .created(uriComponentsBuilder
                        .replacePath("api/user/{userId}")
                        .build(Map.of("userId", myUserEntity.getUserId())))
                .body(myUserEntity);
    }
}
