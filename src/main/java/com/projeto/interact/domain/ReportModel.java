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
    private Long idPost;

    @Column(name = "id_comment")
    private Long idComment;

    //report so possui um user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    public void setId(Long id) {
        this.id = id;
    }

}
