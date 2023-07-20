package com.everyonegarden.crop;

import com.everyonegarden.crop.cropMonth.CropMonth;
import com.everyonegarden.crop.cropMonth.CropMonthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CropService {

    private final CropMonthRepository cropMonthRepository;
    private final CropRepository cropRepository;

    public List<Crop> getAllCropByMonth(int month) {
        List<CropMonth> cropMonthList = cropMonthRepository.findAllByMonths(month);

        return cropMonthList.stream()
                .map(cropMonth -> cropRepository.findById(cropMonth.getCrop().getCropId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}