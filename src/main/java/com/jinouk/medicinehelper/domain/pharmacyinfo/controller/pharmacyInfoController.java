package com.jinouk.medicinehelper.domain.pharmacyinfo.controller;

import com.jinouk.medicinehelper.domain.pharmacyinfo.service.pharmacyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class pharmacyInfoController
{
    private final pharmacyInfoService pharmacyInfoService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<?> test(@RequestHeader(value = "Authorization") String AuthHeader ,
                                  @RequestParam String City,
                                  @RequestParam String Gu) throws IOException
    {
        String token;
        if (AuthHeader != null && AuthHeader.startsWith("Bearer "))
        {
            token = AuthHeader.substring(7);
            System.out.println("Start");
            ResponseEntity<?> res = pharmacyInfoService.requestPharmacyInfo(token , City , Gu);
            System.out.println("End");
            return res;
        }
        else
        {
            throw new IllegalArgumentException("Not Found Any Access Token");
        }
    }
}
