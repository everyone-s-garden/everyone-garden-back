package com.everyonegarden.weather.service.mapper;

import com.everyonegarden.weather.infra.WeatherApiFieldChecker;
import com.everyonegarden.weather.infra.dto.*;
import com.everyonegarden.weather.service.dto.FirstAndSecondDay;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WeatherDtoMapper {

    private final WeatherApiFieldChecker weatherApiFieldChecker;

    public WeatherDtoMapper(WeatherApiFieldChecker weatherApiFieldChecker) {
        this.weatherApiFieldChecker = weatherApiFieldChecker;
    }

    public WeatherApiResult toWeatherApiResult(Map<String, List<WeatherAllApiResponse>> groupedData) {
        return new WeatherApiResult(
                true,
                0,
                groupedData
        );
    }

    public List<WeatherMidResponse> toWeatherMidResponses(JsonArray jsonItemList, int timeFormat, String regionName, FirstAndSecondDay skyOneTwo) {
        List<WeatherMidResponse> result = new ArrayList<>();
        for (JsonElement item : jsonItemList) {
            JsonObject jsonObject = item.getAsJsonObject();
            if (timeFormat <= 12) {
                result.add(new WeatherMidPmApiResponse(jsonObject, skyOneTwo.getFirstDayWeather(), skyOneTwo.getSecondDayWeather(), regionName));
                continue;
            }
            result.add(new WeatherMidAmApiResponse(jsonObject, skyOneTwo.getFirstDayWeather(), skyOneTwo.getSecondDayWeather(), regionName));
        }

        return result;
    }

    public List<WeatherTimeApiResponse> toWeatherTimeApiResponse(JsonArray jsonItemList, String regionName) {
        List<WeatherTimeApiResponse> result = new ArrayList<>();
        for (JsonElement jsonElement : jsonItemList) {
            if (weatherApiFieldChecker.isPopOrSkyOrTmp(jsonElement.getAsJsonObject())) {
                result.add(new WeatherTimeApiResponse(jsonElement.getAsJsonObject(), regionName));
            }
        }
        return result;
    }

    public FirstAndSecondDay toFirstAndSecondDay(JsonArray jsonItemList, String fcstTime, String oneDay, String twoDay) {
        ArrayList<String> skyOneTwo = new ArrayList<>();
        for (JsonElement item : jsonItemList) {
            if (weatherApiFieldChecker.isFirstAndSecondDaySky(item, fcstTime, oneDay, twoDay)) {
                JsonObject jsonObject = (JsonObject) item;
                skyOneTwo.add(jsonObject.get("fcstValue").toString());
            }
        }

        if (skyOneTwo.size() == 1) {
            skyOneTwo.add(skyOneTwo.get(0));
        }
        return new FirstAndSecondDay(skyOneTwo.get(0), skyOneTwo.get(1));
    }

    public List<WeatherAllApiResponse> toWeatherAllApiResponse(JsonArray jsonItemList, String regionName) {
        List<WeatherAllApiResponse> result = new ArrayList<>();
        jsonItemList.forEach(item -> {
            if (weatherApiFieldChecker.isNowT1HOrPTY(item)) {
                result.add(new WeatherAllApiResponse((JsonObject) item, regionName));
            }
        });
        return result;
    }

}
