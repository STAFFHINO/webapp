package storage.services.impl;

import storage.dto.SignUpForm;
import storage.dto.UserDto;
import storage.model.User;
import storage.services.UserMapper;

public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthdate(user.getBirthdate())
                .build();
    }

    @Override
    public User toUser(UserDto form) {
        return User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .birthdate(form.getBirthdate())
                .build();
    }

    @Override
    public User toUser(SignUpForm dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .birthdate(dto.getBirthdate())
                .passwordHash(dto.getPassword())
                .build();
    }
}
