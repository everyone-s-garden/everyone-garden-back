package com.everyonegarden.report.service;

import com.everyonegarden.member.entity.Member;
import com.everyonegarden.member.repository.MemberRepository;
import com.everyonegarden.report.dto.ReportRequestDto;
import com.everyonegarden.report.entity.Report;
import com.everyonegarden.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriterScoreService {
    private final MemberRepository memberRepository;
    private final ReportRepository reportRepository;
    public void sumReportScore(Report report, String writerId, ReportRequestDto dto) {

        String item = dto.getItem();
        int score = 0;
        Member writer = memberRepository.findBySocialId(writerId);

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


        int totalScore = writer.getReportScore() + score;
        writer.setReportScore(totalScore);

        if (totalScore >= 25) {
            writer.setPermanentSusp(true);
        }

        // 작성자 정보 업데이트
        memberRepository.save(writer);
    }

}

