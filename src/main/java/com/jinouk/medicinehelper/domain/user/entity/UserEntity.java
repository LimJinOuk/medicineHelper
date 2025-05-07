package com.jinouk.medicinehelper.domain.user.entity;

import com.jinouk.medicinehelper.domain.user.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    public static UserEntity dtoToEntity(UserDTO dto)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(dto.getName());
        userEntity.setPassword(dto.getPassword());
        userEntity.setEmail(dto.getEmail());
        return userEntity;
    }
}
