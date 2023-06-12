package com.everyonegarden.report.service;


import com.everyonegarden.garden.garden.GardenRepository;
import com.everyonegarden.member.entity.Member;
import com.everyonegarden.report.dto.ReportRequestDto;
import com.everyonegarden.report.entity.Report;
import com.everyonegarden.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {


    private final ReportRepository reportRepository;
    private final GardenRepository gardenRepository;
    private final WriterScoreService writerScoreService;

    public String registerReport(Long postId, Long reporterId, ReportRequestDto reportRequestDto){

        Optional<Report> report = reportRepository.findByPostIdAndReporterId(postId,reporterId);

        //게시글에 대한 신고가 없는 경우
        if(report.isEmpty()){
            Report newReport = Report.builder()
                    .postId(postId)
                    .reporterId(reporterId)
                    .build();
            //저장
            reportRepository.save(newReport);
            Member writer = gardenRepository.findByGardenId(postId).getMember();

            String writerId = writer.getSocialId();
            // 작성자 찾아서 점수 누적하고 영구정지 여부 만들기
            writerScoreService.sumReportScore(newReport,writerId,reportRequestDto);

            return "신고가 완료되었습니다.";
        }

        return "중복된 접수입니다.";
    }


}

