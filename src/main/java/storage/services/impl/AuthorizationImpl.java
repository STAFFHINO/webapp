package storage.services.impl;

import storage.dto.SignInForm;
import storage.dto.SignUpForm;
import storage.dto.UserDto;
import storage.exceptions.StorageEx;
import storage.model.User;
import storage.repositories.UsersRepository;
import storage.services.Authorization;
import lombok.AllArgsConstructor;
import storage.services.PasswordEncoder;
import storage.services.UserMapper;
import java.util.Optional;

@AllArgsConstructor
public class AuthorizationImpl implements Authorization {
    private UsersRepository usersRepository;
    private UserMapper usersMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto signUp(SignUpForm form) throws StorageEx {
        if(form.getEmail() == null){
            throw new StorageEx("Email cannot be null");
        }
        Optional<User> optionalUser = usersRepository.findByEmail(form.getEmail());
        if(optionalUser.isPresent()){
            throw new StorageEx("User with email" + form.getEmail() + " already exist");
        }
        form.setPassword(passwordEncoder.encode(form.getPassword()));
        User user = usersMapper.toUser(form);
        User savedUser = usersRepository.save(user);
        return usersMapper.toDto(savedUser);
    }

    @Override
    public UserDto signIn(SignInForm form) throws StorageEx {
        if(form.getEmail() == null){
            throw new StorageEx("Email cannot be null");
        }
        Optional<User> optionalUser = usersRepository.findByEmail(form.getEmail());
        if(optionalUser.isEmpty()){
            throw new StorageEx("User with email " + form.getEmail() + " not found");
        }
        User user = optionalUser.get();
        if(!passwordEncoder.matches(form.getPassword(), user.getPasswordHash())){
            throw new StorageEx("Wrong password");
        }
        return usersMapper.toDto(user);
    }
}
