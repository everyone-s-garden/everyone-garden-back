package com.everyonegarden.garden.gardenUsing;

import com.everyonegarden.common.PageService;
import com.everyonegarden.common.memberId.MemberId;
import com.everyonegarden.garden.gardenUsing.dto.GardenUsingAddRequest;
import com.everyonegarden.garden.gardenUsing.dto.GardenUsingEditRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("v1/garden/using")
@RestController
public class GardenUsingControllerV1 {

    private final GardenUsingService gardenUsingService;
    private final PageService pageService;

    @GetMapping
    public List<GardenUsingResponse> getGardenUsing(@MemberId Long memberId,
                                                    @RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "size", required = false) Integer size) {
        Pageable pageable = pageService.getPageable(page, size);

        return gardenUsingService.getGardenUsingByMemberId(memberId, pageable).stream()
                .map(GardenUsingResponse::of)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<GardenUsingResponse> addGardenUsing(@MemberId Long memberId,
                                                              @RequestBody @Valid GardenUsingAddRequest gardenUsingAddRequest) {
        GardenUsing added = gardenUsingService.add(gardenUsingAddRequest.toEntity(memberId));

        return ResponseEntity
                .created(URI.create("/v1/garden/" + added.getId()))
                .body(GardenUsingResponse.of(added));
    }

    @PutMapping("{gardenUsingId}")
    public ResponseEntity<GardenUsingResponse> editGardenUsing(@MemberId Long memberId,
                                                               @PathVariable("gardenUsingId") Long gardenUsingId,
                                                               @RequestBody @Valid GardenUsingEditRequest gardenUsingEditRequest) {
        GardenUsing edited = gardenUsingService.edit(gardenUsingEditRequest.toEntity(gardenUsingId, memberId));

        return ResponseEntity.ok(GardenUsingResponse.of(edited));
    }

    @DeleteMapping("{gardenUsingId}")
    public ResponseEntity<String> deleteGardenUsing(@MemberId Long memberId,
                                                    @PathVariable("gardenUsingId") Long gardenUsingId) {
        gardenUsingService.delete(gardenUsingId, memberId);

        return ResponseEntity.noContent().build();
    }

}
