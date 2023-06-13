package com.everyonegarden.report.service;

import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.garden.garden.GardenRepository;
import com.everyonegarden.member.entity.Member;
import com.everyonegarden.member.repository.MemberRepository;
import com.everyonegarden.report.dto.ReportRequestDto;
import com.everyonegarden.report.entity.Report;
import com.everyonegarden.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostScoreService {
    private final GardenRepository gardenRepository;
    public void sumReportScore(Report report, Garden garden, ReportRequestDto dto) {

        String item = dto.getItem();
        int score = 0;

        switch (item) {
            case "허위매물":
                score = 5;
                break;
            case "도배글":
                score = 1;
                break;
            case "욕설":
                score = 3;
                break;
            case "선정성":
                score = 5;
                break;
            case "개인정보노출":
                score = 2;
                break;
            default: {
                score = 1;
                report.setContents(dto.getContent());
                break;
            }

        }


        int totalScore = garden.getReportedScore() + score;
        garden.setReportedScore(totalScore);

        if (totalScore >= 25) {
            garden.setDeleted(true);
        }

        // 작성자 정보 업데이트
        gardenRepository.save(garden);
    }

}

