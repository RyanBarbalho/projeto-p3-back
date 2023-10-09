package com.projeto.interact.controller;

import com.projeto.interact.domain.DTO.AddBoardsDTO;
import com.projeto.interact.domain.UserModel;
import com.projeto.interact.service.implementation.UserBoardService;
import com.projeto.interact.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final UserBoardService userBoardService;

    public UserController(UserService service, UserBoardService userBoardService) {
        this.service = service;
        this.userBoardService = userBoardService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserModel create(@RequestBody UserModel user){
        return service.create(user);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<UserModel> userList = service.findAll();
        return ResponseEntity.ok(userList);
    }

    /*comentei pq n sei nem pq criei esse metodo.
    List<UserModel> findAllByUsername(String username){
    return service.findAllByUsername(username);
    }*/

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

    @PostMapping("/addboards")
    public ResponseEntity<?> addBoards(@RequestBody AddBoardsDTO userBoards) {
        System.out.println("teste");
        System.out.println(userBoards.username());
        System.out.println(userBoards.boardIds());
        userBoardService.addBoardsToUser(userBoards.username(), userBoards.boardIds());
        return ResponseEntity.ok(userBoardService.getUserBoardsByUsername(userBoards.username()));
    }

    @GetMapping("/{username}/boards")
    public ResponseEntity<?> getUserBoardsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userBoardService.getUserBoardsByUsername(username));
    }


}
