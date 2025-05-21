package com.jinouk.medicinehelper.domain.pharmacyinfo.controller;

import com.jinouk.medicinehelper.domain.pharmacyinfo.service.pharmacyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class pharmacyInfoController
{
    private final pharmacyInfoService pharmacyInfoService;

    @GetMapping("/")
    @ResponseBody
    public String test() throws IOException
    {
        System.out.println("Start");
        pharmacyInfoService.requestPharmacyInfo();
        System.out.println("End");
        return "pharmacyInfo";
    }
}
