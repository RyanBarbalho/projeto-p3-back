package com.projeto.interact.service.implementation;

import com.projeto.interact.DTO.UserRegsDTO;
import com.projeto.interact.exceptions.RegisterException;
import com.projeto.interact.model.UserModel;
import com.projeto.interact.respository.UserRepository;
import com.projeto.interact.service.UserService;
import com.projeto.interact.utils.DataBaseUtil;
import com.projeto.interact.utils.Json;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private <T> T findEntityById(JpaRepository<T, Long> repository, Long entityId) {
        //funcao para encontrar qualquer entidade e enviar mensagem de erro em caso de exception
        return repository.findById(entityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found" + " with ID: " + entityId));
    }

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel create(UserModel user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserModel> findAllByUsername(String username){
        return userRepository.findAllByUsername(username);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserModel getUser(Long id){
        return findEntityById(userRepository, id);
    }

    @Override
    public UserModel findByUsername(String username) {
        return userRepository.findUserModelByUsername(username);
    }

    //register user
    @Override
    public ResponseEntity<?> register(UserRegsDTO dto) throws RegisterException {
        UserModel user = new UserModel(dto.getEmail() ,dto.getUsername(), dto.getPassword());
        Json json = new Json();
        try {
            //verifica se há outro com o mesmo nome
            List<UserModel> registeredUsers = userRepository.findAllByUsername(user.getUsername());

            DataBaseUtil.insertCheck(registeredUsers, "User already registered");
            //se o usuario ja estiver cadastrado, lança exception, se nao insere

            userRepository.save(user);
            json.put("status", "created");
        } catch (RegisterException e) {//por enquanto o register Exception ta saindo null
            throw new RegisterException(e.getMessage());
        }
        return ResponseEntity.ok().body(json.toJson());
    }
    //login user


    //post Post

    //post comment
}
