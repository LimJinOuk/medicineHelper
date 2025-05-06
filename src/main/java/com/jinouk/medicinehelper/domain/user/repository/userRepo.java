package com.jinouk.medicinehelper.domain.user.repository;

import com.jinouk.medicinehelper.domain.user.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<userEntity, Integer>
{
    Optional<userEntity> findByName(String name);
}
