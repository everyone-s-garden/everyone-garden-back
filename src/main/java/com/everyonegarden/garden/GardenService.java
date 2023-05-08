package com.everyonegarden.garden;

import com.everyonegarden.garden.dto.GardenPostAddRequest;
import com.everyonegarden.garden.dto.GardenResponse;
import com.everyonegarden.garden.model.GardenPost;
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

    public List<GardenResponse> getPublicGardenByCoordinate(double xStart, double xEnd,
                                                            double yStart, double yEnd) {
        return gardenRepository
                .getPublicGardenByCoordinateWithinRange(xStart, xEnd, yStart, yEnd).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getPrivateGardenByRegion(String region) {
        return List.of();
    }

    public List<GardenResponse> getPrivateGardenByCoordinate(String xStart, String xEnd,
                                                             String yStart, String yEnd) {
        return List.of();
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