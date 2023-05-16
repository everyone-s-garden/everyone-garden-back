package com.everyonegarden.weather.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ApiWeatherRow {


    private String numOfRows; // 페이지
    private String pageNo; // 페이지 수
    private String totalCount; // 데이터 총 개수
    private String resultCode; // 응답 메시지 코드
    private String resultMsg; // 응답 메시지 설명
    private String dataType; // 응답 자료 형식
    private String baseDate; // 날짜
    private String baseTime; // 발료 시간
    private String fcstDate; // 예보 일자
    private String fcstTime; // 예보 시각
    private String category; // 자료 구분 코드
    private Double fcstValue; // 자료에 대한 값
    private String nx; // 경도
    private String ny; // 위도



}
