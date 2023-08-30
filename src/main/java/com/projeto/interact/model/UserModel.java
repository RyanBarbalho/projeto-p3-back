package com.projeto.interact.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name= "users")
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id",unique = true)
    private Long id;

    @Column(nullable = false, length = 25)
    private String username;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(name ="score")
    private int pontuacao;

    @Column(name="role")
    private UserRole role;

    public UserModel(String login, String username, String password, UserRole role) {
        this.login = login;
        this.username = username;
        this.password = password;
        this.pontuacao = 0;
        this.role = role;
    }

    @ManyToMany
    @JoinTable(
            name = "user_boards", joinColumns = @JoinColumn(name = "users", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "boards", referencedColumnName = "board_id"))
    private List<BoardModel> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PostModel> posts;

    @OneToMany(mappedBy = "user")
    private List<CommentModel> comments;


}
