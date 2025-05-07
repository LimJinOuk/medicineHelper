package com.jinouk.medicinehelper.domain.user.service;

import com.jinouk.medicinehelper.domain.user.dto.UserDTO;
import com.jinouk.medicinehelper.domain.user.entity.UserEntity;
import com.jinouk.medicinehelper.domain.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepo userRepo;

    public void register(@RequestBody UserDTO dto)
    {
        UserEntity userentity = UserEntity.dtoToEntity(dto);

        System.out.println(dto);
        userRepo.save(userentity);
    }

    public UserDTO login(@RequestBody UserDTO dto)
    {

        System.out.println(dto);
        System.out.println(dto.getName()); //this is not working WTF?
        Optional<UserEntity> byName = userRepo.findByName(dto.getName());
        System.out.println(byName.isPresent());
        if(byName.isPresent())
        {
            UserEntity userEntity = byName.get();
            if(userEntity.getPassword().equals(dto.getPassword()))
            {
                UserDTO userDTO = new UserDTO();
                userDTO.entityToDTO(userEntity);
                return userDTO;
            }
            else {return null;}
        }
        else {return null;}
    }
}
