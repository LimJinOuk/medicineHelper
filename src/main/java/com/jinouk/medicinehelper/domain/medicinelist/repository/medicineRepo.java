package com.jinouk.medicinehelper.domain.medicinelist.repository;

import com.jinouk.medicinehelper.domain.medicinelist.entity.medicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface medicineRepo extends JpaRepository<medicineEntity, Long>
{
}
