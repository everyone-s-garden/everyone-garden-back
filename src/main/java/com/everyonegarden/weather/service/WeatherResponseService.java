package com.everyonegarden.weather.service;


import com.everyonegarden.weather.dto.ApiWeatherRandomResult;
import com.everyonegarden.weather.dto.ApiWeatherResult;
import com.everyonegarden.weather.dto.ApiWeatherDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherResponseService {



    /**
     * @Method : getApiWeatherResult
     * @Description : weather 결과 처리
     * @Parameter : [list]
     * @Return : ApiResponse
     **/
    public ApiWeatherResult getWeatherResult(List<ApiWeatherDto> list) {
        ApiWeatherResult result = new ApiWeatherResult();
        result.setData(list);
        result.setSuccess(true);
        result.setCode(0);
        return result;
    }

    public ApiWeatherRandomResult getWeatherRandomResult(List<ApiWeatherDto> list, String regionName) {
        ApiWeatherRandomResult result = new ApiWeatherRandomResult();
        result.setData(list);
        result.setRegionName(regionName);
        result.setSuccess(true);
        result.setCode(0);
        return result;
    }


}
