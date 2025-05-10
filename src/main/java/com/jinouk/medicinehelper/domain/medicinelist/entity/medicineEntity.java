package com.jinouk.medicinehelper.domain.medicinelist.entity;

import com.jinouk.medicinehelper.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter

public class medicineEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String medicineName;

    @Column
    int userId;

    @ManyToOne
    @JoinColumn(name = "userId" , nullable = false ,foreignKey = @ForeignKey(name = "medicinelist_ibfk_1"))
    private UserEntity user;

}
