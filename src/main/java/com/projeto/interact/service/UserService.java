package com.projeto.interact.service;

import com.projeto.interact.DTO.UserRegsDTO;
import com.projeto.interact.exceptions.RegisterException;
import com.projeto.interact.model.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    UserModel create(UserModel user);

    List<UserModel> findAllByUsername(String username);

    void deleteUser(Long id);

    UserModel getUser(Long id);

    UserModel findByUsername(String username);
}
