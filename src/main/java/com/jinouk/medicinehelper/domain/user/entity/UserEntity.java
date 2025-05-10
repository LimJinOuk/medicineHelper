package com.jinouk.medicinehelper.domain.user.entity;

import com.jinouk.medicinehelper.domain.medicinelist.entity.medicineEntity;
import com.jinouk.medicinehelper.domain.user.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity@Getter@Setter
@Table(name = "user")
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<medicineEntity> medicinelist = new ArrayList<>();

    public static UserEntity dtoToEntity(UserDTO dto)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(dto.getName());
        userEntity.setPassword(dto.getPassword());
        userEntity.setEmail(dto.getEmail());
        return userEntity;
    }
}
