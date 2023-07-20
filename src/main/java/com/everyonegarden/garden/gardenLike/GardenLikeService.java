package com.everyonegarden.garden.gardenLike;

import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.garden.garden.GardenRepository;
import com.everyonegarden.garden.gardenLike.model.GardenLike;
import com.everyonegarden.garden.gardenLike.model.GardenLikeRepository;
import com.everyonegarden.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GardenLikeService {

    private final GardenRepository gardenRepository;
    private final GardenLikeRepository gardenLikeRepository;

    public List<GardenLike> getAllGardenLikeByMemberId(Long memberId, Pageable pageable) {
        return gardenLikeRepository.findByMemberId(memberId, pageable);
    }

    public boolean isGardenLikedByMember(Long memberId, Long gardenId) {
        if (memberId == null || gardenId == null) return false;

        return gardenLikeRepository.findByMemberIdAndGardenId(memberId, gardenId).isPresent();
    }

    public void addGardenLike(Long memberId, Long gardenId) {
        Optional<Garden> garden = gardenRepository.findById(gardenId);

        if (garden.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찜하시려는 텃밭이 없어요");
        }

        GardenLike gardenLike = GardenLike.builder()
                .gardenId(gardenId)
                .memberId(memberId)
                .garden(garden.get())
                .member(Member.builder().id(memberId).build())
                .build();

        gardenLikeRepository.save(gardenLike);
    }

    public void deleteGardenLike(Long memberId, Long gardenId) {
        Optional<GardenLike> gardenLike = gardenLikeRepository.findByMemberIdAndGardenId(memberId, gardenId);
        if (gardenLike.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 텃밭은 찜한 적이 없어요");
        }

        gardenLikeRepository.deleteByMemberIdAndGardenId(memberId, gardenId);
    }

}