package com.projeto.interact.service.implementation;

import com.projeto.interact.domain.CommentModel;
import com.projeto.interact.domain.CommentVoteModel;
import com.projeto.interact.domain.PostModel;
import com.projeto.interact.domain.PostVoteModel;
import com.projeto.interact.respository.CommentRepository;
import com.projeto.interact.respository.PostRepository;
import com.projeto.interact.respository.PostVoteRepository;
import com.projeto.interact.respository.UserRepository;
import com.projeto.interact.service.PostService;
import com.projeto.interact.utils.FindEntityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class
PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostVoteRepository postVoteRepository;
    private final UserRepository userRepository;


    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository, PostVoteRepository postVoteRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.postVoteRepository = postVoteRepository;
        this.userRepository = userRepository;
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
    public PostModel upvotePost(Long postId, Long userId) {
        PostModel post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            boolean userHasVoted = checkIfUserHasVoted(post, userId);

            if (!userHasVoted) {
                post.setScore(post.getScore() + 1);
                postRepository.save(post);

                PostVoteModel vote = new PostVoteModel();
                vote.setPost(post);
                vote.setUser(userRepository.findById(userId).orElse(null));
                vote.setVoteType(1);
                postVoteRepository.save(vote);
            }
        }

        return post;
    }

    @Override
    public PostModel downvotePost(Long postId, Long userId) {
        PostModel post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            boolean userHasVoted = checkIfUserHasVoted(post, userId);

            if (!userHasVoted) {
                post.setScore(post.getScore() - 1);
                postRepository.save(post);

                PostVoteModel vote = new PostVoteModel();
                vote.setPost(post);
                vote.setUser(userRepository.findById(userId).orElse(null));
                vote.setVoteType(-1);
                postVoteRepository.save(vote);
            }
        }

        return post;
    }


    @Override
    public boolean checkIfUserHasVoted(PostModel post, Long userId) {
        List<PostVoteModel> votes = post.getVotes();

        return votes.stream()
                .anyMatch(vote -> vote.getUser().getId().equals(userId));
    }
    @Override
    public PostModel createComment(long id, CommentModel comment) {
        PostModel post = FindEntityUtil.findEntityById(postRepository, id, "post");
        comment.setPost(post);
        commentRepository.save(comment);
        return post;
    }

    @Override
    public List<CommentModel> getAllComments(long id) {
        //vai fazer a requisição de todos os commentarios do post e eles estarão ordenados pelo score.
        return commentRepository.findAllByPostIdOrderByScoreDesc(id);
    }

    public List<PostModel> findByBoardId (Long boardId) { return postRepository.findByBoardId(boardId); }
}
