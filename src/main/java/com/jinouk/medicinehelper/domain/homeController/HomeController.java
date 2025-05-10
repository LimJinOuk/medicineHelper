package com.jinouk.medicinehelper.domain.homeController;

import com.jinouk.medicinehelper.domain.user.dto.UserDTO;
import com.jinouk.medicinehelper.domain.user.entity.UserEntity;
import com.jinouk.medicinehelper.domain.user.repository.UserRepo;
import com.jinouk.medicinehelper.domain.user.service.CustomUserDetails;
import com.jinouk.medicinehelper.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController
{
    private final UserService service;
    private final UserRepo userRepo;

    @GetMapping("/")
    public String getMain(){return "mainpage/main";}

    @GetMapping("/login")
    public String getLogin() {return "user/login";}

    @GetMapping("/register")
    public String getRegister(){return "user/register";}

    @GetMapping("/mypage")
    public String getMypage(){return "user/mypage";}

    @GetMapping("/mypage")
    public ResponseEntity<?> getMyPage(@AuthenticationPrincipal CustomUserDetails userDetails) {
        int userId = userDetails.getId(); // 이게 DB의 사용자 ID
        return ResponseEntity.ok(userId);
    }


    @GetMapping("/api/user/status")
    public ResponseEntity<?> loginstatus(HttpSession session, Map map)
    {
        Object loginUser = session.getAttribute("loginUser");
        System.out.println(loginUser);
        if (loginUser != null) {
            System.out.println(Map.of("loggedIn", true));
            return ResponseEntity.ok().body(Map.of("loggedIn", true));
        } else {
            return ResponseEntity.ok().body(Map.of("loggedIn", false));
        }
    }

    @PostMapping("doRegister")
    @ResponseBody
    public ResponseEntity<Map<String , String>> register(@RequestBody UserDTO userDTO)
    {
        Map<String , String> map = new HashMap<>();
        service.register(userDTO);
        map.put("Register Result" , "Success");
        return ResponseEntity.ok(map);

    }

    @PostMapping("doLogin")
    @ResponseBody
    public ResponseEntity<Map<String , String>> login(@RequestBody UserDTO userDTO , HttpSession session)
    {
        Map<String , String> map = new HashMap<>();

        System.out.println(userDTO);
        UserEntity userEntity = new UserEntity();
        userEntity.dtoToEntity(userDTO);
        Optional<UserEntity> byName = userRepo.findByName(userDTO.getName());
        System.out.println(byName);
        if(byName.isPresent())
        {
            userEntity = byName.get();
            if(userEntity.getPassword().equals(userDTO.getPassword()))
            {
                map.put("Login Result" , "Success");
                session.setAttribute("loginUser" , byName.get());
                return ResponseEntity.ok().body(map);
            }
            else
            {
                map.put("Login Result" , "Failed");
                return ResponseEntity.ok().body(map);
            }
        }
        else
        {
            map.put("Login Result" , "There is no such user");
            return ResponseEntity.ok().body(map);
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 기존 세션이 있을 때만 가져옴
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return "mainpage/main" ;
    }
}
