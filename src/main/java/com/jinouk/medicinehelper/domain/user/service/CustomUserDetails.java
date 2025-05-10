package com.jinouk.medicinehelper.domain.user.service;

import com.jinouk.medicinehelper.domain.user.entity.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter@Setter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails
{
    private final UserEntity user;

    public int getId() {
        return user.getId();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // 필요하면 역할 추가
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
