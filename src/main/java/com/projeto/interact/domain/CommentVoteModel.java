package com.projeto.interact.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comment_votes")
public class CommentVoteModel {
    @Id
    @GeneratedValue
    @Column(name = "vote_id")
    private Long id;

    @Column(name = "vote_type")
    private Integer voteType;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private CommentModel comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
}
