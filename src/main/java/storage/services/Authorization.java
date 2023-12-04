package storage.services;

import storage.dto.SignInForm;
import storage.dto.SignUpForm;
import storage.dto.UserDto;
import storage.exceptions.StorageEx;

public interface Authorization {
    UserDto signUp(SignUpForm form) throws StorageEx;
    UserDto signIn(SignInForm form) throws StorageEx;
}
