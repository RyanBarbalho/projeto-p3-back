package com.projeto.interact.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
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

    //definir como sera a relação do post
//    @OneToMany
//    private List<PostModel> awnsers = new ArrayList<>();
    //vai ser lista de CommentModels


}
