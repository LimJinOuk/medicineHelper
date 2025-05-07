package com.jinouk.medicinehelper.domain.user.repository;

import com.jinouk.medicinehelper.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer>
{
    Optional<UserEntity> findByName(String name);
}
