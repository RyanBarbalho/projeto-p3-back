package com.projeto.interact.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "posts")
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "text", length = 1000)
    private String text;

    @Column(name = "score")
    private Integer score;


    //um usuario pode fazer mtos posts
    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel user;


    @OneToMany(mappedBy = "post")
    private List<CommentModel> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    private BoardModel board;



}
