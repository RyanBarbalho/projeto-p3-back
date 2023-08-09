package com.projeto.interact.service;

import com.projeto.interact.model.UserModel;
import com.projeto.interact.respository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public void insert(UserModel user){
        userRepository.save(user);
    }

    public List<UserModel> findAllByUsername(String username){
        return userRepository.findAllByUsername(username);
    }
}
