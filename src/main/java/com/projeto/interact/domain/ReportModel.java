package com.projeto.interact.domain;

//denuncias por usuario

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reports")
public class ReportModel {
    //usuario podera denunciar um comentario ou postagem


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id")
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(name = "id_post")
    private String idPost;

    @Column(name = "id_comment")
    private String idComment;

    //muitos ususarios podem fazer muitas denuncias em posts diferentes
    @ManyToMany
    @JoinTable(
            name = "user_reports", joinColumns = @JoinColumn(name = "reports", referencedColumnName = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "users", referencedColumnName = "user_id"))
    private List<UserModel> users = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

}
