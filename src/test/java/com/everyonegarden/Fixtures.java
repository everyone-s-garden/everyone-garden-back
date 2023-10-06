package com.everyonegarden;

import com.everyonegarden.region.entity.Region;
import com.everyonegarden.weather.infra.dto.*;
import com.everyonegarden.weather.service.dto.RegionWeatherFindRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fixtures {

    public static Region seoulRegion() {
        return new Region(
                1L,
                "60",
                "127",
                "11B00000",
                "서울특별시");
    }

    public static Region gangwonRegion() {
        return new Region(
                2L,
                "73",
                "134",
                "11D10000",
                "강원특별자치도");
    }

    public static RegionWeatherFindRequest seoulWeatherFindRequest(String regionId) {
        return new RegionWeatherFindRequest(
                "60",
                "127",
                regionId,
                "서울특별시");
    }

    public static RegionWeatherFindRequest gangwonWeatherFindRequest(String regionId) {
        return new RegionWeatherFindRequest(
                "73",
                "134",
                regionId,
                "강원특별자치도");
    }

    public static WeatherTimeApiResponse gangwonWeatherTimeApiResponse() {
        return new WeatherTimeApiResponse(
                "20231006",
                "TMP",
                "20231006",
                "1200",
                "17",
                "강원"
        );
    }

    public static WeatherMidResponse gangwonWeatherMidResponse() {
        return new WeatherMidPmApiResponse(
                "\"3\"",
                "\"3\"",
                "구름많음",
                "구름많음",
                "맑음",
                "구름많음",
                "맑음",
                "강원"
        );
    }

    public static WeatherApiResult gangwonWeatherApiResult() {
        Map<String, List<WeatherAllApiResponse>> map = new HashMap<>();
        map.put("강원", List.of(gangwonWeatherAllApiResponse()));
        return new WeatherApiResult(
                true,
                0,
                map
        );
    }

    public static WeatherAllApiResponse gangwonWeatherAllApiResponse() {
        return new WeatherAllApiResponse(
                "20231006",
                "PTY",
                "0",
                "강원"
        );
    }

}
//@Getter
//WeatherTimeApiResponse {
//
//    private String baseDate;
//    private String category;
//    private String fcstDate;
//    private String fcstTime;
//    private String fcstValue;
//    private String regionName;
//
//    public WeatherTimeApiResponse(JsonObject item, String regionName) {
//        this.baseDate = item.get("baseDate").getAsString();
//        this.category = item.get("category").getAsString();
//        this.fcstDate = item.get("fcstDate").getAsString();
//        this.fcstTime = item.get("fcstTime").getAsString();
//        this.fcstValue = item.get("fcstValue").getAsString();
//        this.regionName = regionName;
//    }
//
//}
//
//  "강원": [
//          {
//          "baseDate": "20231006",
//          "category": "PTY",
//          "obsrValue": "0",
//          "regionName": "강원"
//          },
//          {
//          "baseDate": "20231006",
//          "category": "T1H",
//          "obsrValue": "16.8",
//          "regionName": "강원"
//          }
//          ],
//          "서울": [
//          {
//          "baseDate": "20231006",
//          "category": "PTY",
//          "obsrValue": "0",
//          "regionName": "서울"
//          },
//          {
//          "baseDate": "20231006",
//          "category": "T1H",
//          "obsrValue": "19",
//          "regionName": "서울"
//          }
//          ],
////week
//          [
//          {
//          "wf1Am": "\"3\"",
//          "wf2Am": "\"3\"",
//          "wf3Am": "구름많음",
//          "wf4Am": "구름많음",
//          "wf5Am": "맑음",
//          "wf6Am": "구름많음",
//          "wf7Am": "맑음",
//          "regionName": "강원"
//          }
//          ]
////time
//          [
//          {
//          "baseDate": "20231006",
//          "category": "TMP",
//          "fcstDate": "20231006",
//          "fcstTime": "1200",
//          "fcstValue": "17",
//          "regionName": "강원"
//          },
//          {
//          "baseDate": "20231006",
//          "category": "SKY",
//          "fcstDate": "20231006",
//          "fcstTime": "1200",
//          "fcstValue": "3",
//          "regionName": "강원"
//          },
//          {
//          "baseDate": "20231006",
//          "category": "POP",
//          "fcstDate": "20231006",
//          "fcstTime": "1200",
//          "fcstValue": "20",
//          "regionName": "강원"
//          },
//          {
//          "baseDate": "20231006",
//          "category": "TMP",
//          "fcstDate": "20231006",
//          "fcstTime": "1300",
//          "fcstValue": "18",
//          "regionName": "강원"
//          },
//          {
//          "baseDate": "20231006",
//          "category": "SKY",
//          "fcstDate": "20231006",
//          "fcstTime": "1300",
//          "fcstValue": "3",
//          "regionName": "강원"
//          },
