package com.everyonegarden.garden;

import com.everyonegarden.garden.dto.*;
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
    private final S3Service s3Service;

    @GetMapping("{type}/by-region")
    public List<GardenResponse> getPublicGardenByRegion(@PathVariable("type") String type,
                                                        @RequestParam("region") String region) {
        GardenTypeRequest gardenTypeRequest = GardenTypeRequest.valueOf(type);

        if (gardenTypeRequest == GardenTypeRequest.PUBLIC) {
            return gardenService.getPublicGardenByRegion(region);
        }

        if (gardenTypeRequest == GardenTypeRequest.RPIVATE) {
            return gardenService.getPrivateGardenByRegion(region);
        }

        return gardenService.getAllGardenByRegion(region);
    }

    @GetMapping("{type}}/by-coordinate")
    public List<GardenResponse> getPublicGardenByCoordinate(@PathVariable("type") String type,
                                                            @RequestParam("lat") String latitude,
                                                            @RequestParam("long") String longitude) {
        GardenTypeRequest gardenTypeRequest = GardenTypeRequest.valueOf(type);

        double latStart = Double.parseDouble(latitude.split(",")[0]);
        double latEnd = Double.parseDouble(latitude.split(",")[1]);
        double longStart = Double.parseDouble(longitude.split(",")[0]);
        double longEnd = Double.parseDouble(longitude.split(",")[1]);

        if (gardenTypeRequest == GardenTypeRequest.PUBLIC) {
            return gardenService.getPublicGardenByCoordinate(latStart, latEnd, longStart, longEnd);
        }

        if (gardenTypeRequest == GardenTypeRequest.RPIVATE) {
            return gardenService.getPrivateGardenByCoordinate(latStart, latEnd, longStart, longEnd);
        }

        return gardenService.getAllGardenByCoordinate(latStart, latEnd, longStart, longEnd);
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
        String uuidUntil5 = UUID.randomUUID().toString().substring(0, 5);
        String fileName = uuidUntil5 + (file.getOriginalFilename() == null ? "" : file.getOriginalFilename().replaceAll(" ", ""));
        byte[] fileBytes = file.getInputStream().readAllBytes();

        String imageUrl = s3Service.putObject(fileName, fileBytes);

        return ImageUploadSuccessResponse.builder()
                .id(uuidUntil5)
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