package com.mingleHub.authsvc.dao;

import com.mingleHub.authsvc.constants.Role;
import com.mingleHub.authsvc.constants.UserStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
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
	
	@Enumerated(EnumType.STRING)
	private UserStatus status;

    @Column(name = "is_email_verified")
    private boolean emailVerified;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

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



