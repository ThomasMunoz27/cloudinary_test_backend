package com.cloudinary_test.demo.Entities;

import com.cloudinary_test.demo.Entities.Enums.Privileges;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
public class User extends Base implements UserDetails {

    @Column(name="email", unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="username", unique = true)
    private String username;

    @Column(name="fecha_registro")
    private LocalDateTime registerDate;

    @Column(name="privilegios")
    private Privileges privileges; //admin o usuario

    @Column(name="public_id_profile_image")
    private String PublicIdProfileImage;

    @Column(name = "link_profile_img")
    private String linkProfileImg;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> imagesPublished;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
