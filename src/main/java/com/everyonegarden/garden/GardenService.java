package com.everyonegarden.garden;

import com.everyonegarden.garden.dto.GardenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GardenService {

    private final GardenRepository gardenRepository;

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

}
