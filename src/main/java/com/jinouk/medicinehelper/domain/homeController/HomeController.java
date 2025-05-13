package com.jinouk.medicinehelper.domain.homeController;

import com.jinouk.medicinehelper.domain.user.dto.UserDTO;
import com.jinouk.medicinehelper.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HomeController
{
    private final UserService service;

    @GetMapping("/")
    public String getMain(){return "mainpage/main";}

    @GetMapping("/login")
    public String getLogin() {return "user/login";}

    @GetMapping("/register")
    public String getRegister(){return "user/register";}

    @GetMapping("/mypage")
    public String getMypage(){return "user/mypage";}

    @GetMapping("/api/user/status")
    public ResponseEntity<?> loginstatus(HttpSession session)
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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 기존 세션이 있을 때만 가져옴
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return "mainpage/main" ;
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
    public ResponseEntity<Map<String , String>> login(@RequestBody UserDTO userDTO , HttpSession session)
    {
       return service.login(userDTO , session);
    }

}
