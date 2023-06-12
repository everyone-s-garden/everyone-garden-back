package com.everyonegarden.weather.service;

import com.everyonegarden.weather.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WeatherResponseService {

    /**
     * @Method : getWeatherResult
     * @Description : weather 결과 처리
     * @Parameter : [groupedData]
     * @Return : ApiWeatherResult
     **/
    public ApiWeatherResult getWeatherAllResult(Map<String, List<ApiWeatherAllDto>> groupedData) {
        ApiWeatherResult result = new ApiWeatherResult();
        result.setData(groupedData);
        result.setSuccess(true);
        result.setCode(0);
        return result;
    }

    public ApiWeatherResult getWeatherPmResult(Map<String, List<ApiWeatherMidPmDto>> groupedData) {
        ApiWeatherResult result = new ApiWeatherResult();
        result.setData(groupedData);
        result.setSuccess(true);
        result.setCode(0);
        return result;
    }
    public ApiWeatherResult getWeatherAmResult(Map<String, List<ApiWeatherMidAmDto>>  groupedData) {
        ApiWeatherResult result = new ApiWeatherResult();
        result.setData(groupedData);
        result.setSuccess(true);
        result.setCode(0);
        return result;
    }

    public ApiWeatherResult getWeatherTimeResult(Map<String, List<ApiWeatherTimeDto>>  groupedData) {
        ApiWeatherResult result = new ApiWeatherResult();
        result.setData(groupedData);
        result.setSuccess(true);
        result.setCode(0);
        return result;
    }
}