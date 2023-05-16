package com.everyonegarden.gardenLike;

import com.everyonegarden.garden.model.Garden;
import com.everyonegarden.gardenLike.model.GardenLike;
import com.everyonegarden.gardenLike.model.GardenLikePk;
import com.everyonegarden.gardenLike.model.GardenLikeRepository;
import com.everyonegarden.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class GardenLikeService {

    private final GardenLikeRepository gardenLikeRepository;

    public List<GardenLike> getAllGardenLikeByMemberId(Long memberId) {
        return gardenLikeRepository.findByMemberId(memberId);
    }

    public void addGardenLike(Long memberId, Long gardenId) {
        GardenLike gardenLike = GardenLike.builder()
                .garden(Garden.builder().gardenId(gardenId).build())
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
