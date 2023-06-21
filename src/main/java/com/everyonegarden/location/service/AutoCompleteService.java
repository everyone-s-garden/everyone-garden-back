package com.everyonegarden.location.service;

import com.everyonegarden.location.Location;
import com.everyonegarden.location.dto.LocationResponse;
import com.everyonegarden.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class AutoCompleteService {

    private final LocationRepository locationRepository;
    public List<LocationResponse> autoCompleteLocation(List<Location> locationList){
        List<LocationResponse> autoLocationList = new ArrayList<>();

        for(Location location : locationList){
            autoLocationList.add(new LocationResponse(location.getFullAddress(),location.getLatitude(), location.getLongitude()));
        }

        return autoLocationList;
    }

    public String[] getAddressLevelArr(String address){
        String[] levelsArr = new String[4];
        String[] tokenArr = address.split(" ");

        for(int i=0;i< tokenArr.length;i++){
            levelsArr[i]=tokenArr[i];
        }
        return levelsArr;
    }

    public int getAddressLevel(String address){
        String[] tokenArr = address.split(" ");

        return tokenArr.length;
    }

    public List<Location> selectQuery(int level, String[] tokenArr, Pageable pageable){
        switch (level){
            case 1:
                 return locationRepository.findAllLocationByLevel1(tokenArr[0], tokenArr[1], tokenArr[2], tokenArr[3],pageable);
            case 2:
                 return locationRepository.findAllLocationByLevel2(tokenArr[0], tokenArr[1], tokenArr[2], tokenArr[3],pageable);
            case 3:
                return locationRepository.findAllLocationByLevel3(tokenArr[0], tokenArr[1], tokenArr[2], tokenArr[3],pageable);
            default:
                return locationRepository.findAllLocationByLevel4(tokenArr[0], tokenArr[1], tokenArr[2], tokenArr[3],pageable);
        }
    }
}
