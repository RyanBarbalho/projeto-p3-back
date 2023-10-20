package com.projeto.interact.domain.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.interact.domain.BoardModel;
import com.projeto.interact.domain.user.UserModel;
import com.projeto.interact.domain.comment.CommentModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "posts")
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "title", length = 100)
    private String title;

    @Setter
    @Column(name = "text", length = 1000000)
    private String text;

    @Column(name = "score")
    private Integer score = 0;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostVoteModel> votes;

    //um usuario pode fazer mtos posts
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserModel user;

    @OneToMany(mappedBy = "post")
    private List<CommentModel> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private BoardModel board;
}
