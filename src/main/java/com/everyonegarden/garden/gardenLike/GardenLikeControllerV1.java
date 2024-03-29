package com.everyonegarden.garden.gardenLike;

import com.everyonegarden.common.PageService;
import com.everyonegarden.common.memberId.MemberId;
import com.everyonegarden.garden.garden.dto.GardenResponse;
import com.everyonegarden.garden.gardenLike.model.GardenLike;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/v1/garden/like")
@RestController
public class GardenLikeControllerV1 {

    private final GardenLikeService gardenLikeService;
    private final PageService pageService;

    @GetMapping("all")
    public List<GardenResponse> getAllGardenLike(@MemberId Long memberId,
                                                 @RequestParam(value = "page", required = false) Integer page,
                                                 @RequestParam(value = "size", required = false) Integer size) {
        Pageable pageable = pageService.getPageableSortedDesc(page, size, "createdDate");
        List<GardenLike> gardenLike = gardenLikeService.getAllGardenLikeByMemberId(memberId, pageable);

        return gardenLike.stream()
                .map(g -> GardenResponse.of(g.getGarden()))
                .collect(Collectors.toList());
    }

    @PostMapping("{gardenId}")
    public ResponseEntity<String> addGardenLike(@MemberId Long memberId,
                                                @PathVariable("gardenId") Long gardenId) {
        gardenLikeService.addGardenLike(memberId, gardenId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("텃밭을 찜했어요");
    }

    @DeleteMapping("{gardenId}")
    public ResponseEntity<String> deleteGardenLike(@MemberId Long memberId,
                                                   @PathVariable("gardenId") Long gardenId) {
        gardenLikeService.deleteGardenLike(memberId, gardenId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("텃밭 찜을 삭제했어요");
    }

}