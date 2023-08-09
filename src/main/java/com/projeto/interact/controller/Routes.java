package com.projeto.interact.controller;

import com.projeto.interact.exceptions.RegisterException;
import com.projeto.interact.utils.DataBaseUtil;
import com.projeto.interact.utils.Json;
import com.projeto.interact.DTO.UserRegsDTO;
import com.projeto.interact.model.UserModel;
import com.projeto.interact.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Routes {
    private UserService userService;
    @PostMapping("demo/register")
    public ResponseEntity<?>cadastro(@RequestBody UserRegsDTO dto){//request
        UserModel user = new UserModel(dto.getEmail() ,dto.getUsername(), dto.getPassword());
        Json json = new Json();
        try {
            List<UserModel> registeredUsers = userService.findAllByUsername(user.getUsername());

            DataBaseUtil.insertCheck(registeredUsers, "User already registered");
            //se o usuario ja estiver cadastrado, lança exception, se nao insere


            userService.insert(user);
        } catch (RegisterException e) {
            json.put("error", e.getMessage());
        }

        json.put("status", "created");
        return ResponseEntity.ok().body(json.toJson());
    }
}
