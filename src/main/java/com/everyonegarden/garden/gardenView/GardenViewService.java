package com.everyonegarden.garden.gardenView;

import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GardenViewService {

    private final GardenViewRepository gardenViewRepository;

    public List<GardenView> getRecentGardenView(Long memberId, Pageable pageable) {
        return gardenViewRepository.findByMemberId(memberId, pageable);
    }

    public Long addGardenView(Long memberId, Long gardenId) {
        GardenView gardenView = GardenView.builder()
                .garden(Garden.builder().gardenId(gardenId).build())
                .member(Member.builder().id(memberId).build())
                .build();

        return gardenViewRepository.save(gardenView).getGardenViewId();
    }

}
