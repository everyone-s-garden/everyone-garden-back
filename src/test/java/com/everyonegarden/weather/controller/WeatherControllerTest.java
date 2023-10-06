package com.everyonegarden.weather.controller;

import com.everyonegarden.Fixtures;
import com.everyonegarden.weather.facade.WeatherFacade;
import com.everyonegarden.weather.infra.dto.WeatherApiResult;
import com.everyonegarden.weather.infra.dto.WeatherMidResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
                                PayloadDocumentation.fieldWithPath("[].baseDate").description("Base date")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].category").description("Category")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].fcstDate").description("Forecast date")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].fcstTime").description("Forecast time")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].fcstValue").description("Forecast value")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].regionName").description("Region name")
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
                                PayloadDocumentation.fieldWithPath("[].wf1Pm").description("wf1Pm description")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].wf2Pm").description("wf2Pm description")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].wf3Pm").description("wf3Pm description")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].wf4Pm").description("wf4Pm description")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].wf5Pm").description("wf5Pm description")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].wf6Pm").description("wf6Pm description")
                                        .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("[].wf7Pm").description("wf7Pm description")
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
                        )));
    }
}


