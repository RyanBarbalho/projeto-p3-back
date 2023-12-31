package com.projeto.interact.respository;

import com.projeto.interact.domain.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    List<UserModel> findAllByUsername(String user);

    UserModel findByLogin(String login);

    UserModel findByUsername(String username);
}
