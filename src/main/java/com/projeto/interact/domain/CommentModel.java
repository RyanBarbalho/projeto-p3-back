package com.projeto.interact.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Data
@Entity
@Table(name = "comments")
public class CommentModel {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "score")
    private Integer score;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user_id", nullable = false)
    private UserModel user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="post_id", nullable = false)
    private PostModel post;


}
