package com.projeto.interact.service.implementation;

import com.projeto.interact.domain.UserModel;
import com.projeto.interact.respository.UserRepository;
import com.projeto.interact.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
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

    public List<UserModel> findAll(){
        return userRepository.findAll();
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
        return userRepository.findByUsername(username);
    }
    //post

    //post comment
}
