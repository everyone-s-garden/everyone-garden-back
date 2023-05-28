package com.everyonegarden.garden;

import com.everyonegarden.garden.dto.GardenDetailResponse;
import com.everyonegarden.garden.dto.GardenPostAddRequest;
import com.everyonegarden.garden.dto.GardenPostResponse;
import com.everyonegarden.garden.dto.GardenResponse;
import com.everyonegarden.garden.gardenImage.GardenImage;
import com.everyonegarden.garden.gardenImage.GardenImageRepository;
import com.everyonegarden.garden.gardenPost.GardenPost;
import com.everyonegarden.garden.gardenPost.GardenPostRepository;
import com.everyonegarden.garden.gardenView.GardenViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GardenService {

    private final GardenRepository gardenRepository;
    private final GardenPostRepository gardenPostRepository;
    private final GardenImageRepository gardenImageRepository;

    private final GardenViewService gardenViewService;

    public List<GardenResponse> getPublicGardenByRegion(String region, Pageable pageable) {
        return gardenRepository.getPublicGardenByRegion(region, pageable).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getPublicGardenByCoordinate(double latStart, double latEnd,
                                                            double longStart, double longEnd,
                                                            Pageable pageable) {
        return gardenRepository
                .getPublicGardenByCoordinateWithinRange(latStart, latEnd, longStart, longEnd, pageable).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getPrivateGardenByRegion(String region, Pageable pageable) {
        return gardenRepository.getPrivateGardenByRegion(region, pageable).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getPrivateGardenByCoordinate(double latStart, double latEnd,
                                                             double longStart, double longEnd,
                                                             Pageable pageable) {
        return gardenRepository
                .getPrivateGardenByCoordinateWithinRange(latStart, latEnd, longStart, longEnd, pageable).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getAllGardenByRegion(String region, Pageable pageable) {
        return gardenRepository
                .getAllGardenByRegion(region, pageable).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getAllGardenByCoordinate(double latStart, double latEnd,
                                                         double longStart, double longEnd,
                                                         Pageable pageable) {
        return gardenRepository
                .getAllGardenByCoordinateWithinRange(latStart, latEnd, longStart, longEnd, pageable).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenPostResponse> getGardenByMemberId(Long memberId, Pageable pageable) {
        List<GardenPost> gardenPosts = gardenPostRepository.findByMemberId(memberId, pageable);

        return gardenPosts.stream()
                .map(GardenPostResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public GardenDetailResponse getGardenDetailByGardenId(Long memberId, Long gardenId) {
        Garden garden = gardenRepository.findById(gardenId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "찾으시는 텃밭이 없어요"));

        if (memberId != null) {
            gardenViewService.addGardenView(memberId, gardenId);
        }

        GardenPost gardenPost = gardenPostRepository.findByGardenId(gardenId)
                .orElse(null);
        List<GardenImage> gardenImages = new ArrayList<>();

        if (gardenPost != null) {
            gardenImages.addAll(gardenImageRepository.findByGardenPostId(gardenPost.getGardenPostId()));
        }

        return GardenDetailResponse.builder()
                .gardenId(garden.getGardenId())

                .address(garden.getAddress())
                .latitude(garden.getLatitude())
                .longitude(garden.getLongitude())

                .name(garden.getName())
                .type(garden.getType().toString())
                .link(garden.getLink())
                .price(Integer.valueOf(garden.getPrice()))
                .contact(garden.getContact())
                .size(garden.getSize())

                .recruitStartDate(LocalDate.from(garden.getRecruitStartDate()))
                .recruitEndDate(LocalDate.from(garden.getRecruitEndDate()))
                .useStartDate(LocalDate.from(garden.getUseStartDate()))
                .useEndDate(LocalDate.from(garden.getUseEndDate()))

                .postTitle(gardenPost == null ? null : gardenPost.getTitle())
                .postContent(gardenPost == null ? null : gardenPost.getContent())
                .images(gardenImages.stream().map(GardenImage::getUrl).collect(Collectors.toList()))

                .build();
    }

    @Transactional
    public Garden addGarden(GardenPostAddRequest gardenPostAddRequest, Long memberId) {
        Garden garden = gardenRepository.save(gardenPostAddRequest.toGardenEntity());
        Long gardenPostId = gardenPostRepository.save(gardenPostAddRequest.toGardenPostEntity(memberId, garden.getGardenId())).getGardenPostId();

        gardenImageRepository.saveAll(gardenPostAddRequest.toGardenImageEntityList(gardenPostId));

        return garden;
    }

    public Garden editGardenNull(Long memberId, Garden editedGarden) {
        Garden gardenToEdit = gardenRepository.findById(editedGarden.getGardenId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수정하시려는 텃밭이 없어요"));

        if (!gardenToEdit.getGardenId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "텃밭을 수정하시려는 권한이 없어요");
        }

        return gardenToEdit.editGarden(editedGarden);
    }

    @Transactional
    public Garden editGardenIgnoreNull(Long memberId, Garden editedGarden) {
        Garden gardenToEdit = gardenRepository.findById(editedGarden.getGardenId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수정하시려는 텃밭이 없어요"));

        if (!gardenToEdit.getGardenId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "텃밭을 수정하시려는 권한이 없어요");
        }

        return gardenToEdit.editGardenIgnoreNull(editedGarden);
    }

    @Transactional
    public void deleteGardenPost(Long memberId, Long gardenId) {
        GardenPost gardenPostToDelete = gardenPostRepository.findByGardenId(gardenId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "지우실려는 텃밭이 없어요"));

        Garden gardenToDelete = gardenRepository.findById(gardenPostToDelete.getGarden().getGardenId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "지우시려는 텃밭이 없어요"));

        if (!gardenPostToDelete.getMember().getId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "텃밭을 지우실 권한이 없어요");
        }

        gardenPostRepository.deleteById(gardenPostToDelete.getGardenPostId());
        gardenRepository.deleteById(gardenToDelete.getGardenId());
    }

}