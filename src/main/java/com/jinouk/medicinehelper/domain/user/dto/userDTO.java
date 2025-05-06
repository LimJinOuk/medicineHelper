package com.jinouk.medicinehelper.domain.user.dto;

import com.jinouk.medicinehelper.domain.user.entity.userEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
@NoArgsConstructor
public class userDTO
{
    private Long id;
    private String name;
    private String email;
    private String password;

    public static userDTO entityToDTO(userEntity userEntity)
    {
        userDTO userDTO = new userDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        return userDTO;
    }

}
