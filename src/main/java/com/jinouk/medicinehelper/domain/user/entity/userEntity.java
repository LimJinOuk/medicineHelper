package com.jinouk.medicinehelper.domain.user.entity;

import com.jinouk.medicinehelper.domain.user.dto.userDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity@Getter@Setter
@Table(name = "user")
public class userEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    public static userEntity dtoToEntity(userDTO dto)
    {
        userEntity userEntity = new userEntity();
        userEntity.setId(dto.getId());
        userEntity.setName(dto.getName());
        userEntity.setPassword(dto.getPassword());
        userEntity.setEmail(dto.getEmail());
        return userEntity;
    }
}
