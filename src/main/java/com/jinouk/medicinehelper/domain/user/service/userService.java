package com.jinouk.medicinehelper.domain.user.service;

import com.jinouk.medicinehelper.domain.user.dto.userDTO;
import com.jinouk.medicinehelper.domain.user.entity.userEntity;
import com.jinouk.medicinehelper.domain.user.repository.userRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class userService
{
    private final userRepo userRepo;

    public void register(@RequestBody userDTO dto)
    {
        userEntity userEntity = new userEntity();
        userEntity.dtoToEntity(dto);

        System.out.println(dto);
        userRepo.save(userEntity);
    }

    public userDTO login(@RequestBody userDTO dto)
    {
        System.out.println(dto);
        Optional<userEntity> byName = userRepo.findByName(dto.getName());
        System.out.println(byName.isPresent());
        if(byName.isPresent())
        {
            userEntity userEntity = byName.get();
            if(userEntity.getPassword().equals(dto.getPassword()))
            {
                userDTO userDTO = new userDTO();
                userDTO.entityToDTO(userEntity);
                return userDTO;
            }
            else {return null;}
        }
        else {return null;}
    }
}
