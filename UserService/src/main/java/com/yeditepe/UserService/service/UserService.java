package com.yeditepe.UserService.service;

import com.yeditepe.UserService.DTO.LoginRequest;
import com.yeditepe.UserService.DTO.UserRequest;
import com.yeditepe.UserService.exception.InvalidCredentialsException;
import com.yeditepe.UserService.exception.UserNotFoundException;
import com.yeditepe.UserService.entity.User;
import com.yeditepe.UserService.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(LoginRequest request) {
        User user = repository.findByEmail(request.email())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.password(), user.getHashedPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return user;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(UserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = new User(request.name(), request.email(), encodedPassword);
        return repository.save(user);
    }

    public User updateUser(Long id, UserRequest request) {
        return repository.findById(id).map(user -> {
            user.setName(request.name());
            user.setEmail(request.email());
            if (request.password() != null) {
                user.setHashedPassword(passwordEncoder.encode(request.password()));
            }
            return repository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> searchUsersByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
