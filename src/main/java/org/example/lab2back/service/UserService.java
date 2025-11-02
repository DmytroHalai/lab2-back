package org.example.lab2back.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.lab2back.entity.CurrencyEntity;
import org.example.lab2back.entity.UserEntity;
import org.example.lab2back.repository.CurrencyRepository;
import org.example.lab2back.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    public UserService(UserRepository userRepository, CurrencyRepository currencyRepository) {
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User by id " + id + " not found"));
    }

    @Transactional
    public UserEntity createUser(String username, String currencyName) {
        CurrencyEntity currency = currencyRepository.findByName(currencyName);
        if (currency == null) {
            currency = currencyRepository.save(new CurrencyEntity(currencyName));
        }
        UserEntity user = new UserEntity(username, currency);
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
}
