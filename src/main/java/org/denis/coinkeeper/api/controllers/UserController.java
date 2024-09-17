package org.denis.coinkeeper.api.controllers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.denis.coinkeeper.api.dto.CurrencyDto;
import org.denis.coinkeeper.api.entities.CurrencyEntity;
import org.denis.coinkeeper.api.entities.UserEntity;
import org.denis.coinkeeper.api.exceptions.BadRequestException;
import org.denis.coinkeeper.api.factories.CurrencyDtoFactory;
import org.denis.coinkeeper.api.factories.UserDtoFactory;
import org.denis.coinkeeper.api.repositories.CurrencyRepository;
import org.denis.coinkeeper.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@RestController
public class UserController {


    private final PasswordEncoder passwordEncoder;

    private final UserDtoFactory userDtoFactory;
    private final UserRepository userRepository;

    private final CurrencyRepository currencyRepository;
    private final CurrencyDtoFactory currencyDtoFactory;


    public static final String MAIN_PAGE = "/api/main";
    public static final String CURRENCY_PAGE = "/api/main/currency";
    public static final String ACCOUNT_PAGE = "/api/main/account";

    public static final String REGISTER_PAGE = "/api/main/register";

    @PostMapping(REGISTER_PAGE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserEntity> register(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            URI uri,
            UriComponentsBuilder uriComponentsBuilder) {

        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        userRepository.save(userEntity);

        return ResponseEntity
                .created(URI.create(CURRENCY_PAGE))
                .body(userEntity);
    }


    @GetMapping(CURRENCY_PAGE)
    @ResponseStatus(HttpStatus.OK)
    public List<CurrencyDto> getCurrencyPage() {
        return currencyRepository.streamAllBy()
                .map(currencyDtoFactory::makeCurrencyDto)
                .collect(Collectors.toList());
    }

    @PostMapping(CURRENCY_PAGE)
    public ResponseEntity<?> createCurrencyPage(@RequestParam(value = "currency") String currencyName,
                                                Authentication authorization) {
        String email = authorization.getName();
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        Optional<CurrencyEntity> currencyEntity = currencyRepository.findByCurrencyName(currencyName);
        if (currencyEntity.isPresent()) {
            UserEntity userResult = userEntity.get();
            userResult.setCurrency(currencyEntity.get());
            userRepository.save(userResult);
            return ResponseEntity
                    .created(URI.create(ACCOUNT_PAGE))
                    .build();
        }
        else {
            throw new BadRequestException(String.format("The currency %s does not exist",currencyName));
        }
    }
}
