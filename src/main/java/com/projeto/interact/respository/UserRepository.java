package com.projeto.interact.respository;

import com.projeto.interact.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    List<UserModel> findAllByUsername(String user);

    UserDetails findByLogin(String login);

    UserModel findByUsername(String username);
}
