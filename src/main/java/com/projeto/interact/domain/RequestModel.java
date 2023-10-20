package com.projeto.interact.domain;

import com.projeto.interact.domain.user.UserModel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "requests")
public class RequestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "board_id")
    private BoardModel board;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    //sera uma imagem do certificado de monitoria
    @Column(name = "certificate")
    @Lob //large object data
    private byte[] certificate;

}
