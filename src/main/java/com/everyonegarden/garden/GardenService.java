package com.everyonegarden.garden;

import com.everyonegarden.garden.dto.GardenPostAddRequest;
import com.everyonegarden.garden.dto.GardenResponse;
import com.everyonegarden.garden.model.GardenImageRepository;
import com.everyonegarden.garden.model.GardenPostRepository;
import com.everyonegarden.garden.model.GardenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GardenService {

    private final GardenRepository gardenRepository;
    private final GardenPostRepository gardenPostRepository;
    private final GardenImageRepository gardenImageRepository;

    public List<GardenResponse> getPublicGardenByRegion(String region) {
        return gardenRepository.getPublicGardenByRegion(region).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getPublicGardenByCoordinate(double latStart, double latEnd,
                                                            double longStart, double longEnd) {
        return gardenRepository
                .getPublicGardenByCoordinateWithinRange(latStart, latEnd, longStart, longEnd).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getPrivateGardenByRegion(String region) {
        return gardenRepository.getPrivateGardenByRegion(region).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getPrivateGardenByCoordinate(double latStart, double latEnd,
                                                             double longStart, double longEnd) {
        return gardenRepository
                .getPrivateGardenByCoordinateWithinRange(latStart, latEnd, longStart, longEnd).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getAllGardenByRegion(String region) {
        return gardenRepository
                .getAllGardenByRegion(region).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getAllGardenByCoordinate(double latStart, double latEnd,
                                                         double longStart, double longEnd) {
        return gardenRepository
                .getAllGardenByCoordinateWithinRange(latStart, latEnd, longStart, longEnd).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long addGarden(GardenPostAddRequest gardenPostAddRequest) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long gardenId = gardenRepository.save(gardenPostAddRequest.toGardenEntity()).getGardenId();
        Long gardenPostId = gardenPostRepository.save(gardenPostAddRequest.toGardenPostEntity(userId, gardenId)).getGardenPostId();

        gardenImageRepository.saveAll(gardenPostAddRequest.toGardenImageEntityList(gardenPostId));

        return gardenId;
    }

}