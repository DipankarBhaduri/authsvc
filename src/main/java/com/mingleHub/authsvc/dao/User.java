package com.mingleHub.authsvc.dao;

import com.mingleHub.authsvc.constants.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "user_info")
public class User implements UserDetails {

    @Id
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "pan_number")
    private String panNumber;

    @Column(name = "is_pan_verified")
    private boolean panVerified;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_phone_verified")
    private boolean phoneVerified;

    private String email;

    @Column(name = "is_email_verified")
    private boolean emailVerified;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
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



