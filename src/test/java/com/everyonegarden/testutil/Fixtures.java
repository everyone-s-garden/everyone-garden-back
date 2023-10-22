package com.everyonegarden.testutil;

import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.garden.garden.GardenStatus;
import com.everyonegarden.garden.garden.GardenType;
import com.everyonegarden.location.model.Location;
import com.everyonegarden.location.service.dto.LocationSearchRequest;
import com.everyonegarden.location.service.dto.LocationSearchResponse;
import com.everyonegarden.location.service.dto.LocationSearchResponses;
import com.everyonegarden.location.service.mapper.LocationDtoMapper;
import com.everyonegarden.member.entity.Member;
import com.everyonegarden.member.entity.Name;
import com.everyonegarden.member.entity.RoleType;
import com.everyonegarden.member.enunerate.MemberProvider;
import com.everyonegarden.region.entity.Region;
import com.everyonegarden.report.controller.dto.ReportRegisterApiRequest;
import com.everyonegarden.report.controller.dto.ReportRegisterApiResponse;
import com.everyonegarden.report.entity.ReportItem;
import com.everyonegarden.report.facade.dto.ReportRegisterFacadeRequest;
import com.everyonegarden.report.service.dto.ReportRegisterRequest;
import com.everyonegarden.weather.infra.dto.*;
import com.everyonegarden.weather.service.dto.RegionWeatherFindRequest;
import org.springframework.stereotype.Component;

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
        return Member.builder()
                .id(1L)
                .email("test@example.com")
                .memberProvider(MemberProvider.GOOGLE)
                .name(new Name("Kim Byeol"))
                .socialId("1234")
                .roleType(RoleType.USER)
                .build();
    }

    public static Member writerMember() {
        return Member.builder()
                .email("test@example.com")
                .memberProvider(MemberProvider.GOOGLE)
                .name(new Name("Kim Byeol"))
                .socialId("1234")
                .roleType(RoleType.USER)
                .build();
    }

    public static Member reporterMember() {
        return Member.builder()
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

    public static ReportRegisterFacadeRequest reportRegisterFacadeRequest() {
        return new ReportRegisterFacadeRequest(
                ReportItem.FAKED_SALE,
                "",
                1L
        );
    }

    public static ReportRegisterApiRequest reportRegisterApiRequest() {
        return new ReportRegisterApiRequest(
                ReportItem.FAKED_SALE.name(),
                "",
                1L
        );
    }

    public static ReportRegisterApiResponse reportRegisterApiResponse() {
        return new ReportRegisterApiResponse(true);
    }

    public static ReportRegisterApiRequest reportRegisterNotExistedReportItemApiRequest() {
        return new ReportRegisterApiRequest(
                "bla bla",
                "",
                1L
        );
    }

    public static ReportRegisterApiRequest reportRegisterBlankReportItemApiRequest() {
        return new ReportRegisterApiRequest(
                " ",
                "",
                1L
        );
    }

    public static ReportRegisterApiRequest reportRegisterNullReportItemApiRequest() {
        return new ReportRegisterApiRequest(
                null,
                "",
                1L
        );
    }

    public static Location 인천광역시() {
        return new Location(
                30D, 20D, "인천광역시", null, null, null, "인천광역시");
    }

    public static Location 인천광역시서구() {
        return new Location(
                30D, 23D, "인천광역시", "서구", null, null, "인천광역시서구");
    }

    public static Location 인천광역시서구신현동() {
        return new Location(
                34D, 40D, "인천광역시", "서구", "신현동", null, "인천광역시서구신현동");
    }

    public static Location 인천광역시서구석남동() {
        return new Location(
                34D, 23D, "인천광역시", "서구", "석남동", null, "인천광역시서구석남동");
    }

    public static Location 인천광역시서구가좌동() {
        return new Location(
                32D, 32D, "인천광역시", "서구", "가좌동", null, "인천광역시서구가좌동");
    }

    public static LocationSearchRequest 인천Request() {
        return new LocationSearchRequest("인천");
    }

    public static List<LocationSearchResponse> 인천LocationSearchResponses() {
        List<LocationSearchResponse> locationSearchResponses = new ArrayList<>();

        locationSearchResponses.add(toLocationSearchResponse(인천광역시()));
        locationSearchResponses.add(toLocationSearchResponse(인천광역시서구()));
        locationSearchResponses.add(toLocationSearchResponse(인천광역시서구신현동()));
        locationSearchResponses.add(toLocationSearchResponse(인천광역시서구석남동()));
        locationSearchResponses.add(toLocationSearchResponse(인천광역시서구가좌동()));

        return locationSearchResponses;
    }

    private static LocationSearchResponse toLocationSearchResponse(Location location) {
        return new LocationSearchResponse(location.assembleFullAddress(), location.getLatitude(), location.getLongitude());
    }

}
