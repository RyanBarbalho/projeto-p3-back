package com.projeto.interact.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name= "users")
@Entity
public class UserModel implements UserDetails{
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

    @Column(name="is_enabled")
    private boolean blocked;

    public UserModel(String login, String username, String password, UserRole role) {
        this.login = login;
        this.username = username;
        this.password = password;
        this.pontuacao = 0;
        this.role = role;
        this.blocked = false;
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


    @Override   //autoridade do sistema
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
