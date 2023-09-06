package com.projeto.interact.controller;

import com.projeto.interact.domain.UserModel;
import com.projeto.interact.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;


    public UserController(UserService service) {
        this.service = service;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserModel create(@RequestBody UserModel user){
        return service.create(user);
    }

    @GetMapping
    public ResponseEntity getAllUsers(){
        List<UserModel> userList = service.findAll();
        return ResponseEntity.ok(userList);
    }

    List<UserModel> findAllByUsername(String username){
        return service.findAllByUsername(username);
    }

    @GetMapping("/{id}")
    UserModel getUser(@PathVariable Long id){
        return service.getUser(id);
    }

    @GetMapping ("/{username}")
    public UserModel findByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }

    //register

    //login

    //post

    //comment

}
