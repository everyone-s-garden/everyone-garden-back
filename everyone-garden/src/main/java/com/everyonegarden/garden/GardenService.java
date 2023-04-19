package com.everyonegarden.garden;

import com.everyonegarden.garden.response.GardenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GardenService {

    private final GardenRepository gardenRepository;

    public List<GardenResponse> getPublicGardenByRegion(String region) {
        return List.of();
    }

    public List<GardenResponse> getPublicGardenByCoordinate(String xStart, String xEnd,
                                                             String yStart, String yEnd) {
        return List.of();
    }

    public List<GardenResponse> getPrivateGardenByRegion(String region) {
        return List.of();
    }

    public List<GardenResponse> getPrivateGardenByCoordinate(String xStart, String xEnd,
                                                             String yStart, String yEnd) {
        return List.of();
    }

}
