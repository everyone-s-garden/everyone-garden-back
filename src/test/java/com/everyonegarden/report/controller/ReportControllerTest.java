package com.everyonegarden.report.controller;

import com.everyonegarden.report.controller.dto.ReportRegisterApiRequest;
import com.everyonegarden.report.controller.dto.ReportRegisterApiResponse;
import com.everyonegarden.report.controller.mapper.ReportApiMapper;
import com.everyonegarden.report.facade.ReportFacade;
import com.everyonegarden.report.facade.dto.ReportRegisterFacadeRequest;
import com.everyonegarden.testutil.Fixtures;
import com.everyonegarden.testutil.WithMockCustomOAuth2LoginUser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockCustomOAuth2LoginUser
@AutoConfigureRestDocs
@WebMvcTest(ReportController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(RestDocumentationExtension.class)
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportFacade reportFacade;

    @MockBean
    private ReportApiMapper reportApiMapper;

    @Test
    void reportPost() throws Exception {
        ReportRegisterApiRequest request = Fixtures.reportRegisterApiRequest();
        ReportRegisterApiResponse response = Fixtures.reportRegisterApiResponse();

        when(reportApiMapper.toReportRegisterFacadeRequest(request)).thenReturn(Fixtures.reportRegisterFacadeRequest());
        when(reportFacade.registerReport(eq(request.postId()), any(ReportRegisterFacadeRequest.class))).thenReturn(response);

        mockMvc.perform(post("/v1/report")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.reportState").value(true))
                .andDo(document("report-post",
                        requestFields(
                                fieldWithPath("item").description("신고 항목"),
                                fieldWithPath("content").description("신고 내용"),
                                fieldWithPath("postId").description("게시글 id")
                        ),
                        responseFields(
                                fieldWithPath("reportState").description("신고 완료 여부")
                        )
                ));

        verify(reportApiMapper, times(1)).toReportRegisterFacadeRequest(request);
        verify(reportFacade, times(1)).registerReport(eq(request.postId()), any(ReportRegisterFacadeRequest.class));
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
