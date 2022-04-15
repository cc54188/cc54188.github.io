package com.example.shareq.services;

import com.example.shareq.models.User;
import com.example.shareq.utils.UserRepository;
import com.example.shareq.utils.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void add(User user) {
        userRepository.save(user);
    }
}
