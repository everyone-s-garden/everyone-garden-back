package com.everyonegarden.batch.mafra;


import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.garden.garden.GardenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MafraWriter implements ItemWriter<List<Garden>> {

    private final GardenRepository gardenRepository;

    @Override
    public void write(List<? extends List<Garden>> gardenList) throws Exception {
        gardenList.forEach(gardenRepository::saveAll);
    }

}
