package com.everyonegarden.crop;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController @RequestMapping("v1/crop")
public class CropControllerV1 {

    private final CropService cropService;

    @GetMapping
    public List<CropResponse> getAllCropByMonth(@RequestParam("month") Integer month) {
        if (month == null) month = LocalDate.now().getMonthValue();

        return cropService.getAllCropByMonth(month).stream()
                .map(CropResponse::of)
                .collect(Collectors.toList());
    }

}
