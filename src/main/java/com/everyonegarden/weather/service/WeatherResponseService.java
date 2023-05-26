package com.everyonegarden.weather.service;


import com.everyonegarden.weather.dto.*;
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
    public ApiWeatherResult getWeatherResult(List< ? extends WeatherDto> list) {
        ApiWeatherResult result = new ApiWeatherResult();
        result.setData(list);
        result.setSuccess(true);
        result.setCode(0);
        return result;
    }


}
