package com.projeto.interact.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "boards")
public class BoardModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="board_id")
    private Long id;
    @Column(name="board_name")
    private String name;

    @OneToMany
    private List<PostModel> posts;

    //json ignore para quando for requisitada nao retornar os usuarios q fazem parte (vai gerar um loop infinito)
    @JsonIgnore
    @ManyToMany(mappedBy = "boards")//possivelmente usar algum cascade type aq
    private List<UserModel> users = new ArrayList<>();

    public BoardModel(String name) {
        this.name = name;
    }
}
