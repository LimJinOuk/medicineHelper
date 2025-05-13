package com.jinouk.medicinehelper.domain.user.service;

import com.jinouk.medicinehelper.domain.user.dto.UserDTO;
import com.jinouk.medicinehelper.domain.user.entity.UserEntity;
import com.jinouk.medicinehelper.domain.user.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
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

    public ResponseEntity<Map<String, String>> login(@RequestBody UserDTO userDTO, HttpSession session)
    {
        Map<String, String> map = new HashMap<>();

        UserEntity.dtoToEntity(userDTO);
        Optional<UserEntity> byEmail = userRepo.findByEmail(userDTO.getEmail());

        if (byEmail.isPresent()) {
//            userEntity = byEmail.get();
            if (byEmail.get().getPassword().equals(userDTO.getPassword())) {
                map.put("Login Result", "Success");
                session.setAttribute("loginUser", byEmail.get());
            } else {
                map.put("Login Result", "Failed");
            }
        } else {
            map.put("Login Result", "There is no such user");
        }
        return ResponseEntity.ok().body(map);
    }
}
