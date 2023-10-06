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
