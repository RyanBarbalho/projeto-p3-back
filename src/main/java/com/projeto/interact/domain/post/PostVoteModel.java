package com.projeto.interact.domain.post;

import com.projeto.interact.domain.user.UserModel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "post_votes")
public class PostVoteModel {
    @Id
    @GeneratedValue
    @Column(name = "vote_id")
    private Long id;

    @Column(name = "vote_type")
    private Integer voteType;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostModel post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
}
