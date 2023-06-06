package com.everyonegarden.garden.garden;

import com.everyonegarden.garden.garden.dto.GardenAddRequest;
import com.everyonegarden.garden.garden.dto.GardenDetailResponse;
import com.everyonegarden.garden.garden.dto.GardenResponse;
import com.everyonegarden.garden.gardenImage.GardenImage;
import com.everyonegarden.garden.gardenImage.GardenImageRepository;
import com.everyonegarden.garden.gardenView.GardenViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GardenService {

    private final GardenRepository gardenRepository;
    private final GardenViewService gardenViewService;
    private final GardenImageRepository gardenImageRepository;

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

    public List<GardenDetailResponse> getGardenByMemberId(Long memberId, Pageable pageable) {
        return gardenRepository.findByMemberId(memberId, pageable).stream()
                .map(GardenDetailResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public GardenDetailResponse getGardenDetailByGardenId(Long memberId, Long gardenId) {
        Garden garden = gardenRepository.findById(gardenId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "찾으시는 텃밭이 없어요"));

        if (memberId != null) {
            gardenViewService.addGardenView(memberId, gardenId);
        }

        return GardenDetailResponse.of(garden);
    }

    @Transactional
    public Garden addGarden(GardenAddRequest gardenAddRequest, Long memberId) {
        Garden addedGarden = gardenRepository.save(gardenAddRequest.toEntity(memberId));

        List<GardenImage> gardenImages = gardenAddRequest.getGardenImages(addedGarden.getGardenId());
        gardenImageRepository.saveAll(gardenImages);

        return addedGarden;
    }

    @Transactional
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
        Garden gardenToDelete = gardenRepository.findById(gardenId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "지우시려는 텃밭이 없어요"));

        if (!gardenToDelete.getMember().getId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "텃밭을 삭제할 권한이 없어요");
        }

        gardenRepository.deleteById(gardenToDelete.getGardenId());
    }

}