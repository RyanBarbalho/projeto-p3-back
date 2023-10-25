package com.projeto.interact.service;

import com.projeto.interact.domain.comment.CommentModel;
import com.projeto.interact.domain.post.PostModel;
import com.projeto.interact.domain.user.UserModel;

import java.util.List;

public interface UserService {
    UserModel create(UserModel user);

    List<UserModel> findAllByUsername(String username);

    void deleteUser(Long id);

    UserModel getUser(Long id);

    UserModel findByUsername(String username);

    List<UserModel> findAll();

    List<PostModel> getAllPostsByUsername(String username);

    List<CommentModel> getAllCommentsByUsername(String username);
}
