package com.jinouk.medicinehelper.domain.pharmacyinfo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PharmacyApiResponse
{
    private Response response;

    @Data
    public static class Response {
        private Header header;
        private PharmacyApiResponse.Body body;
    }

    @Data
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Data
    public static class Body {
        private Items items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Data
    public static class Items {
        private List<Pharmacy> item;
    }

    @Data
    public static class Pharmacy {
        private int rnum;              // 일련번호
        private String dutyAddr;      // 주소
        private String dutyEtc;       // 비고 (옵션)
        private String dutyInf;       // 기관설명상세 (옵션)
        private String dutyMapimg;    // 간이약도 (옵션)
        private String dutyName;      // 기관명
        private String dutyTel1;      // 대표전화1

        private String dutyTime1c;    // 진료시간(월요일) 종료
        private String dutyTime2c;
        private String dutyTime3c;
        private String dutyTime4c;
        private String dutyTime5c;
        private String dutyTime6c;
        private String dutyTime7c;
        private String dutyTime8c;

        private String dutyTime1s;    // 진료시간(월요일) 시작
        private String dutyTime2s;
        private String dutyTime3s;
        private String dutyTime4s;
        private String dutyTime5s;
        private String dutyTime6s;
        private String dutyTime7s;
        private String dutyTime8s;

        private String hpid;          // 기관 ID
        private String postCdn1;      // 우편번호1
        private String postCdn2;      // 우편번호2
        private double wgs84Lon;      // 병원 경도
        private double wgs84Lat;      // 병원 위도
    }
}
