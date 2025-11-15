package org.example.lab2back.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.lab2back.entity.CurrencyEntity;
import org.example.lab2back.entity.UserEntity;
import org.example.lab2back.repository.CurrencyRepository;
import org.example.lab2back.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CurrencyRepository currencyRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User by id " + id + " not found"));
    }

    @Transactional
    public UserEntity createUser(String username, String password, String currencyName) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Користувач з таким ім'ям вже існує");
        }

        CurrencyEntity currency = currencyName != null ?
                currencyRepository.findByName(currencyName) : null;
        if (currency == null && currencyName != null) {
            currency = currencyRepository.save(new CurrencyEntity(currencyName));
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        user.setCurrencyEntity(currency);

        return userRepository.save(user);
    }

    @Transactional
    public UserEntity setCurrency(Long userId, String currencyName) {
        CurrencyEntity currency = currencyRepository.findByName(currencyName);
        if (currency == null) {
            currency = currencyRepository.save(new CurrencyEntity(currencyName));
        }
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setCurrencyEntity(currency);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) throw new EntityNotFoundException("User not found");
        userRepository.deleteById(id);
    }

    public void deleteAllCurrencies() {
        currencyRepository.deleteAll();
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}