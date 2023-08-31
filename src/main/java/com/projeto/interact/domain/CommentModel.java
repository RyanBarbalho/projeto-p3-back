package com.projeto.interact.domain;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "comments")
public class CommentModel {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserModel user; //autor do comentario

    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private PostModel post;//post do comentarios


}
