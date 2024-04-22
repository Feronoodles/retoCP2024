package com.example.demo.entities;


import com.example.demo.dto.user.DtoUser;
import com.example.demo.dto.user.DtoUserLogin;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    private String token;
    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    private static final long serialVersionUID = 1L;
    @PrePersist
    public void prePersist(){
        createAt = new Date();
    }

    public User(DtoUser dtoUser)
    {
        this.user_id = dtoUser.getUser_id();
        this.username = dtoUser.getUsername();
        this.password = dtoUser.getPassword();
    }
    public User(DtoUserLogin dtoUser)
    {
        this.username = dtoUser.getUsername();
        this.password = dtoUser.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
