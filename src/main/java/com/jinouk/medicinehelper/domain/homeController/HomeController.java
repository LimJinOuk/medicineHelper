package com.jinouk.medicinehelper.domain.homeController;

import com.jinouk.medicinehelper.global.jwt.jwtUtil.jwtUtil;
import com.jinouk.medicinehelper.domain.user.dto.UserDTO;
import com.jinouk.medicinehelper.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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
    public ResponseEntity<Map<String , String>> register(@RequestBody UserDTO userDTO)
    {
        Map<String , String> map = new HashMap<>();
        service.register(userDTO);
        map.put("Register Result" , "Success");
        return ResponseEntity.ok(map);

    }

    @PostMapping("/DoLogin")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO)
    {
        System.out.println(userDTO);
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Map<String , String>> loginresult = service.login(userDTO);

        String token = jwtUtil.generateToken(userDTO.getName());
        String RefreshToken = jwtUtil.generateRefresh(userDTO.getName());

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", RefreshToken)
                .httpOnly(true)
                .secure(true) // HTTPS만 사용할 경우 true TODO : 이거 배포시 꼭 true로 바꾸기
                .path("/")
                .maxAge( 24 * 60 * 60) // 7일 유지
                .sameSite("Strict")
                .build();

        headers.set("Authorization", "Bearer " + token);
        headers.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());
        System.out.println("login result: " + loginresult.getBody());
        System.out.println("AccessToken" + token);
        System.out.println("RefreshToken" + refreshCookie.toString());

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .header("Access-Control-Expose-Headers", "Authorization")
                .body(loginresult.getBody());
    }

}
