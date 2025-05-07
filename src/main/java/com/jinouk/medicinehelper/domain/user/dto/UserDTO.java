package com.jinouk.medicinehelper.domain.user.dto;

import com.jinouk.medicinehelper.domain.user.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
@NoArgsConstructor
public class UserDTO
{
    private String name;
    private String email;
    private String password;

    public static UserDTO entityToDTO(UserEntity userEntity)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        return userDTO;
    }

}
