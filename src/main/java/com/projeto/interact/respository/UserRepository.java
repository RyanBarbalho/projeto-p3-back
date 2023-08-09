package com.projeto.interact.respository;

import com.projeto.interact.model.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    List<UserModel> findAllByUsername(String user);

    UserModel findUserModelByUsername(String user);
}
