package com.everyonegarden.garden.garden;

import com.everyonegarden.garden.garden.dto.GardenAddRequest;
import com.everyonegarden.garden.garden.dto.GardenResponse;
import com.everyonegarden.garden.gardenImage.GardenImage;
import com.everyonegarden.garden.gardenImage.GardenImageRepository;
import com.everyonegarden.garden.gardenView.GardenViewService;
import com.everyonegarden.member.entity.Member;
import com.everyonegarden.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GardenService {

    private final GardenRepository gardenRepository;
    private final GardenViewService gardenViewService;
    private final GardenImageRepository gardenImageRepository;
    private final MemberRepository memberRepository;

    public List<GardenResponse> getGardenByQuery(String query, Pageable pageable) {
        return gardenRepository.findAllGardenByQuery(query, pageable).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getAllGarden(Pageable pageable) {
        return gardenRepository.findAllGarden(pageable).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

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

    public List<GardenResponse> getGardenByMemberId(Long memberId, Pageable pageable) {
        return gardenRepository.findByMemberId(memberId, pageable).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public GardenResponse getGardenDetailByGardenId(Long memberId, Long gardenId) {
        Garden garden = gardenRepository.findById(gardenId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "찾으시는 텃밭이 없어요"));

        if (memberId != null) {
            gardenViewService.addGardenView(memberId, gardenId);
        }

        return GardenResponse.of(garden);
    }

    @Transactional
    public Garden addGarden(GardenAddRequest gardenAddRequest, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "올바른 유저가 아니에요"));

        Garden gardenToAdd = gardenRepository.save(gardenAddRequest.toEntity(member));
        for (String url : gardenAddRequest.getImages()) {
            GardenImage gardenImage = GardenImage.builder()
                    .url(url)
                    .build();

            gardenToAdd.addImage(gardenImage);
        }

        return gardenRepository.save(gardenToAdd);
    }

    @Transactional
    public Garden editGarden(Long memberId, Garden editedGarden) {
        Garden gardenToEdit = gardenRepository.findById(editedGarden.getGardenId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수정하시려는 텃밭이 없어요"));

        if (!gardenToEdit.getMember().getId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "텃밭을 수정하시려는 권한이 없어요");
        }

        List<GardenImage> existingGardenImages = gardenToEdit.getImages();
        List<GardenImage> imagesCopy = new ArrayList<>(existingGardenImages);

        for (GardenImage newImage : editedGarden.getImages()) {
            if (!imagesCopy.contains(newImage)) {
                gardenToEdit.addImage(newImage);
                continue;
            }

            imagesCopy.remove(newImage);
        }

        for (GardenImage imageToRemove : imagesCopy) {
            gardenToEdit.removeImage(imageToRemove);
            gardenImageRepository.delete(imageToRemove);
        }

        return gardenToEdit.editGarden(editedGarden);
    }

    @Transactional
    public void deleteGardenPost(Long memberId, Long gardenId) {
        Garden gardenToDelete = gardenRepository.findById(gardenId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "지우시려는 텃밭이 없어요"));

        if (!gardenToDelete.getMember().getId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "텃밭을 삭제할 권한이 없어요");
        }

        gardenRepository.deleteById(gardenToDelete.getGardenId());
    }

}
