package org.denis.coinkeeper.api.factories;

import org.denis.coinkeeper.api.dto.UserDto;
import org.denis.coinkeeper.api.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDtoFactory {
    public UserDto makeUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .userId(userEntity.getUserId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}
