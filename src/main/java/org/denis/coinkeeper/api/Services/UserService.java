package org.denis.coinkeeper.api.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.denis.coinkeeper.api.dto.*;
import org.denis.coinkeeper.api.entities.CurrencyEntity;
import org.denis.coinkeeper.api.entities.ExpensesEntity;
import org.denis.coinkeeper.api.entities.ProfitEntity;
import org.denis.coinkeeper.api.entities.UserEntity;
import org.denis.coinkeeper.api.exceptions.BadRequestException;
import org.denis.coinkeeper.api.factories.CurrencyDtoFactory;
import org.denis.coinkeeper.api.factories.ExpensesDtoFactory;
import org.denis.coinkeeper.api.factories.ProfitDtoFactory;
import org.denis.coinkeeper.api.factories.UserDtoFactory;
import org.denis.coinkeeper.api.repositories.CurrencyRepository;
import org.denis.coinkeeper.api.repositories.ExpensesRepository;
import org.denis.coinkeeper.api.repositories.ProfitRepository;
import org.denis.coinkeeper.api.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserDtoFactory userDtoFactory;
    private final UserRepository userRepository;

    private final CurrencyRepository currencyRepository;
    private final CurrencyDtoFactory currencyDtoFactory ;

    private final ExpensesRepository expensesRepository;
    private final ExpensesDtoFactory expensesDtoFactory;

    private final ProfitRepository profitRepository;
    private final ProfitDtoFactory profitDtoFactory;


    @Transactional
    public UserEntity register(UserAuthDto userAuthDto) {

        String currencyDefault = "RUB";
        Optional<CurrencyEntity> currencyEntityOptional = currencyRepository.findByCurrencyName(currencyDefault);
        if (currencyEntityOptional.isPresent()) {

            UserEntity userEntity = UserEntity.builder()
                    .email(userAuthDto.getEmail())
                    .password(passwordEncoder.encode(userAuthDto.getPassword()))
                    .currency(currencyEntityOptional.get())
                    .build();

            return userRepository.save(userEntity);
        }
        else {
            throw new BadRequestException("Server Error with Currency");
        }
    }


    @Transactional
    public void patchUser(String email,
                          UserDto userDto) {

        Optional<UserEntity> userEntity = userRepository.findByEmail(email);

        UserEntity userEntity1 = userEntity.get();

        Optional<CurrencyEntity> currencyEntity = currencyRepository.findByCurrencyName(userDto.getCurrency().getCurrencyName());

        if (currencyEntity.isEmpty())
            throw new BadRequestException(String.format("The currency \"%s\" does not exist",userDto.getCurrency().getCurrencyName()));

        if (!userEntity1.getEmail().equals(userDto.getEmail())) {
            userEntity1.setEmail(userDto.getEmail());
        }
        if (!userEntity1.getCurrency().equals(currencyEntity.get())) {
            userEntity1.setCurrency(currencyEntity.get());
        }
        if (!userEntity1.getAccount().equals(userDto.getAccount())) {
            userEntity1.setAccount(userDto.getAccount());
        }
        userRepository.save(userEntity1);
    }

    public UserDto getUser(String email) {

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        if (userEntityOptional.isEmpty())
            throw new UsernameNotFoundException("this user not found");

        return userDtoFactory.makeUserDto(userEntityOptional.get());
    }

    public List<UserDto> getUsers() {

        return userRepository.streamAllBy()
                .map(userDtoFactory::makeUserDto)
                .toList();
    }


    @Transactional
    public void removeUser(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isPresent()) {
            userRepository.delete(userEntity.get());
        }
        else {
            throw new BadRequestException("this user not found");
        }
    }
    public UserFinanceDto getFinanceUser(String email) {


         List<ProfitEntity> profitList = profitRepository.getProfitList(email);
         List<ExpensesEntity> expensesList = expensesRepository.getExpensesList(email);
         return UserFinanceDto.builder()
                 .profitEntityList(profitList
                         .stream()
                         .map(profitDtoFactory::makeProfitDto)
                         .toList())
                 .expensesEntityList(expensesList
                         .stream()
                         .map(expensesDtoFactory::makeExpensesDto)
                         .toList())
                 .build();
    }
}
