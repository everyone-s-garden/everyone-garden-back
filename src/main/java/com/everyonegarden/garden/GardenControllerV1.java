package com.everyonegarden.garden;

import com.everyonegarden.garden.api.mafra.fetch.MafraFetchService;
import com.everyonegarden.garden.dto.GardenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/garden")
public class GardenControllerV1 {

    private final GardenService gardenService;
    private final MafraFetchService mafraFetchService;

    @GetMapping("public/by-region")
    public List<GardenResponse> getPublicGardenByRegion(@RequestParam("region") String region) {
        mafraFetchService.getMafraApiResponse(0, 10);

        return gardenService.getPublicGardenByRegion(region);
    }

    @GetMapping("public/by-coordinate")
    public List<GardenResponse> getPublicGardenByCoordinate(@RequestParam("x") String xCoordinate,
                                                            @RequestParam("y") String yCoordinate) {
        double xStart = Double.parseDouble(xCoordinate.split(",")[0]);
        double xEnd = Double.parseDouble(xCoordinate.split(",")[1]);
        double yStart = Double.parseDouble(yCoordinate.split(",")[0]);
        double yEnd = Double.parseDouble(yCoordinate.split(",")[1]);

        return gardenService.getPublicGardenByCoordinate(xStart, xEnd, yStart, yEnd);
    }

    @GetMapping("private/by-region")
    public List<GardenResponse> getPrivateGardenByRegion(@RequestParam("region") String region) {
        return gardenService.getPrivateGardenByRegion(region);
    }

    @GetMapping("private/by-coordionate")
    public List<GardenResponse> getPrivateGardenByCoordinate(@RequestParam("x") String xCoordinate,
                                                             @RequestParam("y") String yCoordinate) {
        double xStart = Double.parseDouble(xCoordinate.split(",")[0]);
        double xEnd = Double.parseDouble(xCoordinate.split(",")[1]);
        double yStart = Double.parseDouble(yCoordinate.split(",")[0]);
        double yEnd = Double.parseDouble(yCoordinate.split(",")[1]);

        return gardenService.getPublicGardenByCoordinate(xStart, xEnd, yStart, yEnd);
    }

    @GetMapping("recent")
    public List<GardenResponse> getRecentlyViewdGarden() {
        return List.of();
    }

    @GetMapping("mine")
    public List<GardenResponse> getMyGarden() {
        return List.of();
    }

    @GetMapping("{gardenId}")
    public GardenResponse getGardenDetail(@PathVariable("gardenId") Long gardenId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Long> addGarden() {
        return null;
    }

    @PutMapping("{gardenId}")
    public ResponseEntity<GardenResponse> editGarden(@PathVariable("gardenId") Long gardenId) {
        return null;
    }

    @DeleteMapping("{gardenId}")
    public ResponseEntity<String> deleteGarden(@PathVariable("gardenId") Long gardenId) {
        return null;
    }

}