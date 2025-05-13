package com.jinouk.medicinehelper.domain.medicinelist.repository;

import com.jinouk.medicinehelper.domain.medicinelist.entity.medicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface medicineRepo extends JpaRepository<medicineEntity, Long>
{
    Optional<medicineEntity> findById(int userId);
}
