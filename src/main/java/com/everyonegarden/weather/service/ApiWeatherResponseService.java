package com.everyonegarden.weather.service;


import com.everyonegarden.weather.dto.ApiWeatherResult;
import com.everyonegarden.weather.dto.ApiWeatherDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiWeatherResponseService {



    /**
     * @Method : getApiWeatherResult
     * @Description : weather 결과 처리
     * @Parameter : [list]
     * @Return : ApiResponse
     **/
    public ApiWeatherResult getWeatherResult(List<ApiWeatherDto> list) {
        ApiWeatherResult result = new ApiWeatherResult();
        result.setData(list);
        setSuccessResult(result);
        return result;
    }

    /**
     * @Method : setSuccessResult
     * @Description : 결과에 api 요청 성공 데이터 세팅
     * @Parameter : [result]
     * @Return : null
     **/
    private void setSuccessResult(ApiWeatherResult result) {
        result.setSuccess(true);
        result.setCode(0);
    }




}
