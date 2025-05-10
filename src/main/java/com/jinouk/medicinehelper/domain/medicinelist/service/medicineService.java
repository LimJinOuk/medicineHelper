package com.jinouk.medicinehelper.domain.medicinelist.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@NoArgsConstructor
@RequiredArgsConstructor
public class medicineService
{
    public ResponseEntity<Map<String , String>> getmedicinelist()
    {
        Map<String , String> map = new HashMap<>();
        map.put("Status" , "Success");


        return ResponseEntity.ok(map);
    }

}
