package com.jinouk.medicinehelper.domain.homeController;

import com.jinouk.medicinehelper.domain.user.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class homeController
{
    private final userService service;

}
