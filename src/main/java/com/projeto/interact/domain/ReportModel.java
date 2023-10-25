package com.projeto.interact.domain;

//denuncias por usuario

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.interact.domain.user.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String reason;

    @Column(name = "id_post")
    private Long idPost;

    @Column(name = "id_comment")
    private Long idComment;

    @Column()
    private boolean answered;

    @Column()
    private Long timeout;

    //report so possui um user
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

    public void setId(Long id) {
        this.id = id;
    }

}
