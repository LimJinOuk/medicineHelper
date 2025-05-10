package com.jinouk.medicinehelper.domain.user.service;

import com.jinouk.medicinehelper.domain.user.entity.UserEntity;
import com.jinouk.medicinehelper.domain.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService
{
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username은 일반적으로 이메일이나 이름 등
        UserEntity user = userRepo.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 없음"));

        return new CustomUserDetails(user);
    }
}
