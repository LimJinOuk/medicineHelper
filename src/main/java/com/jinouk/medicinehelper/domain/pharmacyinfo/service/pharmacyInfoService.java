package com.jinouk.medicinehelper.domain.pharmacyinfo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinouk.medicinehelper.domain.pharmacyinfo.DTO.PharmacyApiResponse;
import com.jinouk.medicinehelper.global.jwt.jwtUtil.jwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class pharmacyInfoService
{
    private final jwtUtil jwtUtil;

    @Value("${openAPI.AuthKey}")
    private String openAPIKey;

    public ResponseEntity<?> requestPharmacyInfo(String token , String City , String Gu) throws IOException , IllegalArgumentException
    {
        if(jwtUtil.validateToken(token))
        {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire");
            //Use Open API Service Auth Key
            urlBuilder.append("?" + URLEncoder.encode("serviceKey" , "UTF-8") + "=" + openAPIKey);
            urlBuilder.append("&" + URLEncoder.encode("Q0","UTF-8") + "=" + URLEncoder.encode(City, "UTF-8")); /*주소(시도)*/
            urlBuilder.append("&" + URLEncoder.encode("Q1","UTF-8") + "=" + URLEncoder.encode(Gu, "UTF-8")); /*주소(시군구)*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*목록 건수*/
            urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON*/
            //Make url
            URL url = new URL(urlBuilder.toString());
            //Connect to URL With HTTP
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            //Read The Response Code
            int responseCode = conn.getResponseCode();
            //Set The Input Stream
            InputStream is = conn.getInputStream();
            //Read The InputStream and make them to StringBuilder instance
            BufferedReader rd = new BufferedReader(new InputStreamReader(is , "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            //Make InputStream StringBuilder instance to String
            String jsonString = sb.toString();

            //Make InputStream String to JSON
            ObjectMapper mapper = new ObjectMapper();
            PharmacyApiResponse apiResponse = mapper.readValue(jsonString, PharmacyApiResponse.class);

            Map<String , String> map = new HashMap<>();

            List<PharmacyApiResponse.Pharmacy> pharmacies =
                    apiResponse.getResponse().getBody().getItems().getItem();

            for (PharmacyApiResponse.Pharmacy p : pharmacies) {
                map.put("약국 이름: " , p.getDutyName());
                map.put("전화번호: " , p.getDutyTel1());
                map.put("주소: " , p.getDutyAddr());
                map.put("월요일 시작 시각: " , p.getDutyTime1s());
                map.put("월요일 종료 시각: " , p.getDutyTime1c());
                map.put("화요일 시작 시각: " , p.getDutyTime2s());
                map.put("화요일 종료 시각: " , p.getDutyTime2c());
                map.put("수요일 시작 시각: " , p.getDutyTime3s());
                map.put("수요일 종료 시각: " , p.getDutyTime3c());
                map.put("목요일 시작 시각: " , p.getDutyTime4s());
                map.put("목요일 종료 시각: " , p.getDutyTime4c());
                map.put("금요일 시작 시각: " , p.getDutyTime5s());
                map.put("금요일 종료 시각: " , p.getDutyTime5c());
                map.put("토요일 시작 시각: " , p.getDutyTime6s());
                map.put("토요일 종료 시각: " , p.getDutyTime6c());
                map.put("일요일 시작 시각: " , p.getDutyTime7s());
                map.put("일요일 종료 시각: " , p.getDutyTime7c());
                map.put("공휴일 시작 시각: " , p.getDutyTime8s());
                map.put("공휴일 종료 시각: " , p.getDutyTime8c());
            }
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        else {
            throw new JwtException("Invalid Access Token");
        }

    }

}
