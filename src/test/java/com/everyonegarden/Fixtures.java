package com.everyonegarden;

import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.garden.garden.GardenStatus;
import com.everyonegarden.garden.garden.GardenType;
import com.everyonegarden.garden.gardenImage.GardenImage;
import com.everyonegarden.member.entity.Member;
import com.everyonegarden.member.entity.Name;
import com.everyonegarden.member.entity.RoleType;
import com.everyonegarden.member.enunerate.MemberProvider;
import com.everyonegarden.region.entity.Region;
import com.everyonegarden.report.entity.ReportItem;
import com.everyonegarden.report.service.dto.ReportRegisterRequest;
import com.everyonegarden.weather.infra.dto.*;
import com.everyonegarden.weather.service.dto.RegionWeatherFindRequest;

import java.util.ArrayList;
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

    public static ReportRegisterRequest fakeSaleReportRequest(Long postId) {
        return new ReportRegisterRequest(
                ReportItem.FAKED_SALE,
                "",
                postId
        );
    }

    public static Garden gangwonGarden(Member member) {
        return new Garden(
                "강원도 춘천시 퇴계로 89",
                37.757687,
                128.873749,
                "강원양떼농장",
                GardenType.PUBLIC,
                GardenStatus.ACTIVE,
                "http://samplegarden.com",
                "$10 per hour",
                "010-1234-4567",
                "10평",
                "텃밭을 가꾸는 데 아주 좋은 시설을 가지고 있어요",
                true,
                false,
                true,
                member,
                false,
                0
        );
    }

    public static Garden editedGangwonGarden(Member member) {
        return new Garden(
                "강원도 춘천시 퇴계로 89",
                37.757687,
                128.873749,
                "강원양떼농장",
                GardenType.PUBLIC,
                GardenStatus.INACTIVE,
                "http://samplegarden.com",
                "$15 per hour",
                "010-1234-4567",
                "10평",
                "텃밭을 가꾸는 데 아주 좋은 시설을 가지고 있어요",
                true,
                false,
                true,
                member,
                false,
                0
        );
    }
    public static Member savedMember() {
        return  Member.builder()
                .id(1L)
                .email("test@example.com")
                .memberProvider(MemberProvider.GOOGLE)
                .name(new Name("Kim Byeol"))
                .socialId("1234")
                .roleType(RoleType.USER)
                .build();
    }

    public static Member writerMember() {
        return  Member.builder()
                .email("test@example.com")
                .memberProvider(MemberProvider.GOOGLE)
                .name(new Name("Kim Byeol"))
                .socialId("1234")
                .roleType(RoleType.USER)
                .build();
    }

    public static Member reporterMember() {
        return  Member.builder()
                .email("test@example.com")
                .memberProvider(MemberProvider.GOOGLE)
                .name(new Name("Park JinGyeom"))
                .socialId("1234")
                .roleType(RoleType.USER)
                .build();
    }

    public static ReportRegisterRequest reportRegisterRequest(Long postId) {
        return new ReportRegisterRequest(
                ReportItem.FAKED_SALE,
                "",
                postId
        );
    }

}
