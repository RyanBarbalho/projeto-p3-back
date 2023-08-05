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
    @Column(name="user_id")
    private Long id;
    
    @Column(name = "email")
    private String email;

    @Column(name = "username", length = 25)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name ="pontuacao")
    private int pontuacao;

    @Column(name = "monitor")
    private boolean monitor;

    public UserModel(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.pontuacao = 0;
        this.monitor = false;
    }

    @ManyToMany
    @JoinTable(
            name = "user_boards", joinColumns = @JoinColumn(name = "users", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "badges", referencedColumnName = "board_id"))
    private List<BoardModel> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PostModel> posts;


}
