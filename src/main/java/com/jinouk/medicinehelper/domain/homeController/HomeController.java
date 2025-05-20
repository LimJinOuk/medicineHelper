package com.jinouk.medicinehelper.domain.homeController;

import com.jinouk.medicinehelper.global.jwt.jwtUtil.jwtUtil;
import com.jinouk.medicinehelper.domain.user.dto.UserDTO;
import com.jinouk.medicinehelper.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController
{
    private final UserService service;
    private final jwtUtil jwtUtil;

    @Autowired
    public HomeController(UserService service, jwtUtil jwtUtil)
    {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/")
    public String getMain(){return "mainpage/main";}

    @GetMapping("/login")
    public String getLogin() {return "user/login";}

    @GetMapping("/register")
    public String getRegister(){return "user/register";}

    @GetMapping("/mypage")
    public String getMypage(){return "user/mypage";}


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
    public ResponseEntity<Map<String , String>> login(@RequestBody UserDTO userDTO)
    {
        ResponseEntity<Map<String , String>> loginresult = service.login(userDTO);

        String token = jwtUtil.generateToken(userDTO.getName() , "Role_User");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        return ResponseEntity
                .status(loginresult.getStatusCode())
                .headers(headers)
                .body(loginresult.getBody());
    }

}
