package org.denis.coinkeeper.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.denis.coinkeeper.api.Services.CurrencyService;
import org.denis.coinkeeper.api.Services.UserService;
import org.denis.coinkeeper.api.dto.*;
import org.denis.coinkeeper.api.entities.CurrencyEntity;
import org.denis.coinkeeper.api.entities.UserEntity;
import org.denis.coinkeeper.api.factories.CurrencyDtoFactory;
import org.denis.coinkeeper.api.factories.UserDtoFactory;
import org.denis.coinkeeper.api.repositories.CurrencyRepository;
import org.denis.coinkeeper.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@RestController
public class UserController {


    private final UserService userService;
    private final CurrencyService currencyService;

    public static final String CURRENCIES_API_ENDPOINT = "/api/currency";

    public static final String USER_API_ENDPOINT = "/api/user";
    public static final String REMOVE_USER_API_ENDPOINT = "/api/user/remove";
    public static final String USER_EDIT_API_ENDPOINT = "/api/user/edit";
    public static final String USERS_API_ENDPOINT = "/api/users";
    public static final String REGISTER_API_ENDPOINT = "/api/register";

    @PostMapping(REGISTER_API_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserEntity> register(@RequestBody @Valid UserAuthDto userAuthDto,
                                               BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        UserEntity userEntity = userService.register(userAuthDto);

        return ResponseEntity
                .created(URI.create(CURRENCIES_API_ENDPOINT))
                .body(userEntity);
    }

    @GetMapping(CURRENCIES_API_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CurrencyDto>> getCurrencyPage() {

        List<CurrencyDto> currencyDtoList = currencyService.getCurrencyDtoList();

        return ResponseEntity
                .ok(currencyDtoList);
    }

    @GetMapping(USER_API_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserSummaryDto> getUser(Authentication authorization) {
        String email = authorization.getName();

        UserDto user = userService.getUser(email);
        UserFinanceDto userFinance = userService.getFinanceUser(email);

        return ResponseEntity
                .ok(UserSummaryDto.builder()
                        .userDto(user)
                        .userFinanceDto(userFinance)
                        .build());
    }

//    @DeleteMapping(REMOVE_USER_API_ENDPOINT)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseEntity<?> deleteUser(Authentication authorization) {
//        String email = authorization.getName();
//
//        userService.removeUser(email);
//
//        return ResponseEntity
//                .noContent();
//    }

    @PatchMapping(USER_EDIT_API_ENDPOINT)
    public ResponseEntity<?> editUserProfile(@RequestBody UserDto userDto,
                                             Authentication authorization) {
        String email = authorization.getName();

        userService.patchUser(email,userDto);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping(USERS_API_ENDPOINT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> userDtoList = userService.getUsers();
        return ResponseEntity
                .ok(userDtoList);
    }
//
//    @PostMapping(CURRENCY_API_ENDPOINT)
//    @ResponseStatus
//    public ResponseEntity<?> createUserCurrency(@RequestParam(value = "currency") String currencyName,
//                                                Authentication authorization) {
//        String email = authorization.getName();
//        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
//        Optional<CurrencyEntity> currencyEntity = currencyRepository.findByCurrencyName(currencyName);
//        if (currencyEntity.isPresent()) {
//            UserEntity userResult = userEntity.get();
//            userResult.setCurrency(currencyEntity.get());
//            userRepository.save(userResult);
//
//            return ResponseEntity
//                    .created(URI.create(ACCOUNT_API_ENDPOINT))
//                    .build();
//        }
//        else {
//            throw new BadRequestException(String.format("The currency \"%s\" does not exist",currencyName));
//        }
//    }
//
//    @PostMapping(ACCOUNT_API_ENDPOINT)
//    public ResponseEntity<?> createUserAccount(@RequestParam(value = "account") Long account,
//                                                Authentication authorization) {
//        String email = authorization.getName();
//        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
//        UserEntity userResult = userEntity.get();
//        userResult.setAccount(account);
//        userRepository.save(userResult);
//        return ResponseEntity
//                .created(URI.create(USER_API_ENDPOINT))
//                .build();
//    }
//
}