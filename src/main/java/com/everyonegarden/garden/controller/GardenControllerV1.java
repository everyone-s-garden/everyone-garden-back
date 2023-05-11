package com.everyonegarden.garden.controller;

import com.everyonegarden.garden.service.GardenService;
import com.everyonegarden.garden.api.mafra.fetch.MafraFetchService;
import com.everyonegarden.garden.dto.GardenAddSuccessResponse;
import com.everyonegarden.garden.dto.GardenPostAddRequest;
import com.everyonegarden.garden.dto.GardenResponse;
import com.everyonegarden.garden.dto.ImageUploadSuccessResponse;
import com.everyonegarden.garden.s3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/garden")
public class GardenControllerV1 {

    private final GardenService gardenService;
    private final MafraFetchService mafraFetchService;
    private final S3Service s3Service;

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
    public GardenAddSuccessResponse addGarden(@RequestBody @Valid GardenPostAddRequest gardenAddRequest) {
        Long gardenId = gardenService.addGarden(gardenAddRequest);

        return GardenAddSuccessResponse.builder()
                .gardenId(gardenId)
                .build();
    }

    @SneakyThrows
    @PostMapping("images")
    public ImageUploadSuccessResponse uploadImage(@RequestParam("file") MultipartFile file) {
        String fileName = UUID.randomUUID().toString().substring(0, 5) + (file.getOriginalFilename() == null ? "" : file.getOriginalFilename().replaceAll(" ", ""));
        byte[] fileBytes = file.getInputStream().readAllBytes();

        String imageUrl = s3Service.putObject(fileName, fileBytes);

        return ImageUploadSuccessResponse.builder()
                .id(UUID.randomUUID())
                .imageUrl(imageUrl)
                .build();
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