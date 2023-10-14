package com.everyonegarden.weather.controller;

import com.everyonegarden.testutil.Fixtures;
import com.everyonegarden.weather.facade.WeatherFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(WeatherController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(RestDocumentationExtension.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherFacade weatherFacade;

    @Test
    public void weatherRegionRequest() throws Exception {
        // Arrange
        when(weatherFacade.getRegionWeather("127.456", "37.123"))
                .thenReturn(Arrays.asList(Fixtures.gangwonWeatherTimeApiResponse()));

        // Act & Assert
        mockMvc.perform(get("/v1/weather/time")
                        .param("lat", "37.123")
                        .param("long", "127.456"))
                .andExpect(status().isOk())
                .andDo(document("weather-region-request",
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("[].baseDate").description("예측날짜")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].category").description("종류")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].fcstDate").description("예보날짜")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].fcstTime").description("예보시간")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].fcstValue").description("예측값")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].regionName").description("지역 이름")
                                        .type(JsonFieldType.STRING)
                        )));
    }

    @Test
    public void weatherWeekRequest() throws Exception {
        // Arrange
        when(weatherFacade.getWeekWeather("127.456", "37.123"))
                .thenReturn(Arrays.asList(Fixtures.gangwonWeatherMidResponse()));

        // Act & Assert
        mockMvc.perform(get("/v1/weather/week")
                        .param("lat", "37.123")
                        .param("long", "127.456"))
                .andExpect(status().isOk())
                .andDo(document("weather-week-request",
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("[].wf1Pm").description("1일차 오후")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].wf2Pm").description("2일차 오후")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].wf3Pm").description("3일차 오후")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].wf4Pm").description("4일차 오후")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].wf5Pm").description("5일차 오후")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].wf6Pm").description("6일차 오후")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].wf7Pm").description("7일차 오후")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].regionName").description("Region name")
                                        .type(JsonFieldType.STRING)
                        )));
    }

    @Test
    public void weatherAllRequest() throws Exception {
        // Arrange
        when(weatherFacade.getAllRegionWeather())
                .thenReturn(Fixtures.gangwonWeatherApiResult());

        // Act & Assert
        mockMvc.perform(get("/v1/weather/all"))
                .andExpect(status().isOk())
                .andDo(document("weather-all-request",
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").description("Success flag")
                                        .type(JsonFieldType.BOOLEAN),
                                PayloadDocumentation.fieldWithPath("code").description("Status code")
                                        .type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("data").description("Data object")
                                        .type(JsonFieldType.OBJECT)
                                        .optional(),  // data 필드가 없는 경우를 허용
                                PayloadDocumentation.fieldWithPath("data.강원").description("강원 지역의 날씨 정보")
                                        .type(JsonFieldType.ARRAY).optional(),  // "강원" 필드가 없는 경우를 허용
                                PayloadDocumentation.fieldWithPath("data.강원[].baseDate")
                                        .description("날씨 기준 날짜").type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("data.강원[].category")
                                        .description("날씨 카테고리").type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("data.강원[].obsrValue")
                                        .description("관측 값").type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("data.강원[].regionName")
                                        .description("지역 이름").type(JsonFieldType.STRING)
                        )));
    }
}


