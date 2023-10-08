package com.everyonegarden.report.service;

import com.everyonegarden.Fixtures;
import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.garden.garden.GardenRepository;
import com.everyonegarden.member.entity.Member;
import com.everyonegarden.member.repository.MemberRepository;
import com.everyonegarden.report.entity.Report;
import com.everyonegarden.report.exception.DuplicatedReportException;
import com.everyonegarden.report.repository.ReportRepository;
import com.everyonegarden.report.service.dto.ReportRegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ReportServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportRepository reportRepository;

    private Member writer;
    private Member reporter;
    private Garden garden;

    @BeforeEach
    void setUp() {
        dataSetUp();
    }

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
        gardenRepository.deleteAll();
        reportRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글에 대한 첫 신고가 잘 등록되는지 확인한다.")
    void registerReport_firstReport_returnIsPresent() {
        //given
        ReportRegisterRequest reportRegisterRequest = Fixtures.reportRegisterRequest(garden.getGardenId());

        //when
        reportService.registerReport(reporter.getId(), reportRegisterRequest);
        Optional<Report> byPostIdAndReporterId = reportRepository.findByPostIdAndReporterId(garden.getGardenId(), reporter.getId());

        //then
        assertThat(byPostIdAndReporterId.isPresent()).isEqualTo(true);
    }

    @Test
    @DisplayName("게시글에 대한 같은 신고자가 중복된 신고를 접수하는 경우 예외를 던진다.")
    void registerReport_duplicatedReport_throwException() {
        //given
        ReportRegisterRequest reportRegisterRequest = Fixtures.reportRegisterRequest(garden.getGardenId());
        reportService.registerReport(reporter.getId(), reportRegisterRequest);

        //when_then
        assertThrows(DuplicatedReportException.class,()->reportService.registerReport(reporter.getId(),reportRegisterRequest));
    }

    void dataSetUp() {
        Member writerToSave = Fixtures.writerMember();
        Member reporterToSave = Fixtures.reporterMember();

        writer = memberRepository.save(writerToSave);
        reporter = memberRepository.save(reporterToSave);

        Garden gardenToSave = Fixtures.gangwonGarden(writer);
        garden = gardenRepository.save(gardenToSave);

    }
}
