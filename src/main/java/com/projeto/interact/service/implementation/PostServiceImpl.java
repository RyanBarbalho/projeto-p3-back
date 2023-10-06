package com.projeto.interact.service.implementation;

import com.projeto.interact.domain.CommentModel;
import com.projeto.interact.domain.PostModel;
import com.projeto.interact.respository.PostRepository;
import com.projeto.interact.service.PostService;
import com.projeto.interact.utils.FindEntityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostModel create(PostModel post) {

        return postRepository.save(post);
    }

    @Override
    public PostModel getPost(long id) {
        return FindEntityUtil.findEntityById(postRepository, id, "post");
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostModel> findAll() {
        return postRepository.findAll();
    }

    @Override
    public PostModel upvotePost(long id) {
        PostModel post = FindEntityUtil.findEntityById(postRepository, id, "post");
        post.setScore(post.getScore() + 1);
        return postRepository.save(post);
    }

    @Override
    public PostModel downvotePost(long id) {
        PostModel post = FindEntityUtil.findEntityById(postRepository, id, "post");
        post.setScore(post.getScore() - 1);
        return postRepository.save(post);
    }

    @Override
    public PostModel createComment(long id, CommentModel comment) {
        PostModel post = FindEntityUtil.findEntityById(postRepository, id, "post");
        post.getComments().add(comment);
        postRepository.save(post);
        return post;
    }
}
