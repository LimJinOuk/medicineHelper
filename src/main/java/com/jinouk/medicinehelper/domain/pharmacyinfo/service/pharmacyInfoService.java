package com.jinouk.medicinehelper.domain.pharmacyinfo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinouk.medicinehelper.domain.pharmacyinfo.DTO.PharmacyApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Service
public class pharmacyInfoService
{
    @Value("${openAPI.AuthKey}")
    private String openAPIKey;

    public void requestPharmacyInfo() throws IOException
    {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire");
        //Use Open API Service Auth Key
        urlBuilder.append("?" + URLEncoder.encode("serviceKey" , "UTF-8") + "=" + openAPIKey);
        urlBuilder.append("&" + URLEncoder.encode("Q0","UTF-8") + "=" + URLEncoder.encode("서울특별시", "UTF-8")); /*주소(시도)*/
        urlBuilder.append("&" + URLEncoder.encode("Q1","UTF-8") + "=" + URLEncoder.encode("강남구", "UTF-8")); /*주소(시군구)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*목록 건수*/
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

        //Make URL
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


        List<PharmacyApiResponse.Pharmacy> pharmacies =
                apiResponse.getResponse().getBody().getItems().getItem();

        for (PharmacyApiResponse.Pharmacy p : pharmacies) {
            System.out.println("약국 이름: " + p.getDutyName());
            System.out.println("전화번호: " + p.getDutyTel1());
            System.out.println("주소: " + p.getDutyAddr());
            System.out.println("월요일 시작 시각: " + p.getDutyTime1s());
            System.out.println("월요일 종료 시각: " + p.getDutyTime1c());
            System.out.println("화요일 시작 시각: " + p.getDutyTime2s());
            System.out.println("화요일 종료 시각: " + p.getDutyTime2c());
            System.out.println("수요일 시작 시각: " + p.getDutyTime3s());
            System.out.println("수요일 종료 시각: " + p.getDutyTime3c());
            System.out.println("목요일 시작 시각: " + p.getDutyTime4s());
            System.out.println("목요일 종료 시각: " + p.getDutyTime4c());
            System.out.println("금요일 시작 시각: " + p.getDutyTime5s());
            System.out.println("금요일 종료 시각: " + p.getDutyTime5c());
            System.out.println("토요일 시작 시각: " + p.getDutyTime6s());
            System.out.println("토요일 종료 시각: " + p.getDutyTime6c());
            System.out.println("일요일 시작 시각: " + p.getDutyTime7s());
            System.out.println("일요일 종료 시각: " + p.getDutyTime7c());
            System.out.println("공휴일 시작 시각: " + p.getDutyTime8s());
            System.out.println("공휴일 종료 시각: " + p.getDutyTime8c());
        }
    }

}
