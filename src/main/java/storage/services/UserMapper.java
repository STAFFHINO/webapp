package storage.services;

import storage.dto.SignUpForm;
import storage.dto.UserDto;
import storage.model.User;

public interface UserMapper {
    UserDto toDto(User user);
    User toUser(UserDto form);
    User toUser(SignUpForm dto);
}
