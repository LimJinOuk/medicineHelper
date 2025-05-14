package com.jinouk.medicinehelper.domain.user.service;

import com.jinouk.medicinehelper.domain.user.dto.UserDTO;
import com.jinouk.medicinehelper.domain.user.entity.UserEntity;
import com.jinouk.medicinehelper.domain.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public void register(@RequestBody UserDTO dto) {
        UserEntity userentity = UserEntity.dtoToEntity(dto);
        System.out.println(dto);
        userRepo.save(userentity);
    }

    public ResponseEntity<Map<String, String>> login(@RequestBody UserDTO userDTO) throws IllegalArgumentException
    {
        Map<String, String> map = new HashMap<>();

        UserEntity.dtoToEntity(userDTO);
        Optional<UserEntity> byEmail = userRepo.findByEmail(userDTO.getEmail());

        if (byEmail.isPresent()) {
            if (byEmail.get().getPassword().equals(userDTO.getPassword())) {
                map.put("Login_Result" , "Success");
            } else 
            {
                throw new IllegalArgumentException("비밀번호가 조회되지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("유저 정보가 없습니다.");
        }
        return ResponseEntity.ok().body(map);
    }
}
