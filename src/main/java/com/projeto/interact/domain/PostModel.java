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

    private String title;

    private String text;

    //um usuario pode fazer mtos posts
    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel user;


    @OneToMany(mappedBy = "post")
    private List<CommentModel> comments;



}
