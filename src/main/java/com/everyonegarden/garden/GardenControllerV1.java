package com.everyonegarden.garden;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/garden")
public class GardenControllerV1 {

    private final GardenService gardenService;

    @GetMapping("public/by-region")
    public List<GardenResponse> getPublicGardenByRegion(@RequestParam("region") String region) {
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

}