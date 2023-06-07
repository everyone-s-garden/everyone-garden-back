package com.everyonegarden.weather.service.midterm;


import com.everyonegarden.weather.entity.Region;
import com.everyonegarden.weather.repository.RegionRepository;
import com.everyonegarden.weather.service.WeatherResponseService;
import com.everyonegarden.weather.service.shortterm.time.WeatherTimeApiService;
import com.everyonegarden.weather.service.shortterm.reversegeo.ReverseGeoFetchService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class GetOneTwoDayService {

    private final RegionRepository regionRepository ;
    private final WeatherTimeApiService weatherTimeApiService;
    public final WeatherResponseService weatherResponseService;

    public final ReverseGeoFetchService reverseGeoFetchService;


    public ArrayList<String> getSkyOneTwo(Region region) throws Exception {


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH");


        //시각
        String fcstTime = sdfTime.format(calendar.getTime())+"00";

        // 오늘 날짜
        String oneday = sdfDay.format(calendar.getTime());


        // 내일 날짜
        calendar.add(Calendar.DATE, 1);
        String twoday = sdfDay.format(calendar.getTime());


        ArrayList<String> skyOneTwo = new ArrayList<>();

        JsonArray jsonItemList = weatherTimeApiService.shortWeather(region.getNx(),region.getNy());
        for(Object o : jsonItemList){
            JsonObject item = (JsonObject) o;
            if(checkSky(item,fcstTime,oneday,twoday))
                skyOneTwo.add(item.get("fcstValue").toString());

        }

        if(skyOneTwo.size()==1){
            skyOneTwo.add(skyOneTwo.get(0));
        }

        return skyOneTwo;

    }

    public boolean checkSky(JsonObject item,String time, String oneday,String twoday) {

        String category = item.get("category").getAsString();
        String fcstDate = item.get("fcstDate").getAsString();
        String fcstTime = item.get("fcstTime").getAsString();

        if (category.equals("SKY")
                && fcstDate.equals(oneday)
                && fcstTime.equals(time))
            return true;

        if(category.equals("SKY")
                && fcstDate.equals(twoday)
                && fcstTime.equals(time))
            return true;

        return false;
    }





}
