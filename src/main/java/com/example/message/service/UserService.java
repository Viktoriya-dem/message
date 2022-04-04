package com.example.message.service;

import com.example.message.entity.User;
import com.example.message.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepo userRepo;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public User findByLogin(String username){
        return userRepo.findByUsername(username);
    }


    public User findByNameAndPassword(String username, String password) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("User not found in the Database");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found in the Database: {}", username);
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
