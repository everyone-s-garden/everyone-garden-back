package com.everyonegarden.garden.garden;

import com.everyonegarden.common.PageService;
import com.everyonegarden.global.exception.UnauthorizedException;
import com.everyonegarden.common.memberId.MemberId;
import com.everyonegarden.global.config.s3.S3Service;
import com.everyonegarden.garden.garden.dto.*;
import com.everyonegarden.garden.gardenLike.GardenLikeService;
import com.everyonegarden.garden.gardenView.GardenViewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("v1/garden")
@RestController
public class GardenControllerV1 {

    private final GardenService gardenService;
    private final GardenViewService gardenViewService;
    private final GardenLikeService gardenLikeService;

    private final S3Service s3Service;
    private final PageService pageService;

    @GetMapping
    public List<GardenResponse> getGardenByQuery(@RequestParam("query") String query,
                                                 @RequestParam(value = "page", required = false) Integer page,
                                                 @RequestParam(value = "size", required = false) Integer size) {
        Pageable pageable = pageService.getPageable(page, size);

        return gardenService.getGardenByQuery(query, pageable);
    }

    @GetMapping("all")
    public List<GardenResponse> getAllGarden(@RequestParam(value = "page", required = false) Integer page,
                                             @RequestParam(value = "size", required = false) Integer size) {
        Pageable pageable = pageService.getPageable(page, size);

        return gardenService.getAllGarden(pageable);
    }

    @GetMapping("{type}/by-region")
    public List<GardenResponse> getPublicGardenByRegion(@PathVariable("type") String type,
                                                        @RequestParam("region") String region,
                                                        @RequestParam(value = "page", required = false) Integer page,
                                                        @RequestParam(value = "size", required = false) Integer size) {
        Pageable pageable = pageService.getPageable(page, size);

        GardenTypeRequest gardenTypeRequest;
        try {
            gardenTypeRequest = GardenTypeRequest.valueOf(type.toUpperCase());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("%s은 올바른 범위가 아닙니다", type));
        }

        if (gardenTypeRequest == GardenTypeRequest.PUBLIC) {
            return gardenService.getPublicGardenByRegion(region, pageable);
        }

        if (gardenTypeRequest == GardenTypeRequest.PRIVATE) {
            return gardenService.getPrivateGardenByRegion(region, pageable);
        }

        return gardenService.getAllGardenByRegion(region, pageable);
    }

    @GetMapping("{type}/by-coordinate")
    public List<GardenResponse> getPublicGardenByCoordinate(@PathVariable("type") String type,
                                                            @RequestParam("lat") String latitude,
                                                            @RequestParam("long") String longitude,
                                                            @RequestParam(value = "page", required = false) Integer page,
                                                            @RequestParam(value = "size", required = false) Integer size) {
        Pageable pageable = pageService.getPageable(page, size);

        GardenTypeRequest gardenTypeRequest;
        try {
            gardenTypeRequest = GardenTypeRequest.valueOf(type.toUpperCase());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("%s은 올바른 범위가 아닙니다", type));
        }

        double latStart = Double.parseDouble(latitude.split(",")[0]);
        double latEnd = Double.parseDouble(latitude.split(",")[1]);
        double longStart = Double.parseDouble(longitude.split(",")[0]);
        double longEnd = Double.parseDouble(longitude.split(",")[1]);

        if (gardenTypeRequest == GardenTypeRequest.PUBLIC) {
            return gardenService.getPublicGardenByCoordinate(latStart, latEnd, longStart, longEnd, pageable);
        }

        if (gardenTypeRequest == GardenTypeRequest.PRIVATE) {
            return gardenService.getPrivateGardenByCoordinate(latStart, latEnd, longStart, longEnd, pageable);
        }

        return gardenService.getAllGardenByCoordinate(latStart, latEnd, longStart, longEnd, pageable);
    }

    @GetMapping("recent")
    public List<GardenResponse> getRecentlyViewedGarden(@MemberId Long memberId,
                                                        @RequestParam(value = "page", required = false) Integer page,
                                                        @RequestParam(value = "size", required = false) Integer size) {
        Pageable pageable = pageService.getPageableSortedDesc(page, size, "gardenViewId");

        return gardenViewService
                .getRecentGardenView(memberId, pageable).stream()
                .map(GardenResponse::of)
                .collect(Collectors.toList());
    }

    @GetMapping("mine")
    public List<GardenResponse> getMyGarden(@MemberId Long memberId,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size) {
        Pageable pageable = pageService.getPageableSortedDesc(page, size, "gardenId");

        return gardenService.getGardenByMemberId(memberId, pageable);
    }

    @GetMapping("{gardenId}")
    public GardenResponse getGardenDetail(@MemberId Long memberId,
                                          @PathVariable("gardenId") Long gardenId) {
        GardenResponse garden = gardenService.getGardenDetailByGardenId(memberId, gardenId);
        return garden.updateIsLiked(gardenLikeService.isGardenLikedByMember(memberId, gardenId));
    }

    @PostMapping
    public ResponseEntity<GardenResponse> addGarden(@MemberId Long memberId,
                                                    @RequestBody @Valid GardenAddRequest gardenAddRequest) {
        if (memberId == null) throw new UnauthorizedException("token이 올바르지 않아요");

        Garden garden = gardenService.addGarden(gardenAddRequest, memberId);

        return ResponseEntity
                .created(URI.create(String.format("v1/garden/%s", garden.getGardenId())))
                .body(GardenResponse.of(garden));
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
    public ResponseEntity<GardenResponse> editGardenSelectively(@MemberId Long memberId,
                                                                @PathVariable("gardenId") Long gardenId,
                                                                @RequestBody @Valid GardenEditRequest gardenEditRequest) {
        Garden garden = gardenService.editGarden(memberId, gardenEditRequest.toEntity(gardenId));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GardenResponse.of(garden));
    }

    @DeleteMapping("{gardenId}")
    public ResponseEntity<String> deleteGarden(@MemberId Long memberId,
                                               @PathVariable("gardenId") Long gardenId) {
        gardenService.deleteGardenPost(memberId, gardenId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("텃밭을 지웠어요");
    }

}
