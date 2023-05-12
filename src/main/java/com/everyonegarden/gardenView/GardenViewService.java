package com.everyonegarden.gardenView;

import com.everyonegarden.garden.model.Garden;
import com.everyonegarden.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class GardenViewService {

    private final GardenViewRepository gardenViewRepository;

    public List<GardenView> getRecentGardenView(Long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());

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
