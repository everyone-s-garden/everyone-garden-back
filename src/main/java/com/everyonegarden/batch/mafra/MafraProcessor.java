package com.everyonegarden.batch.mafra;

import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.garden.garden.GardenType;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MafraProcessor implements ItemProcessor<ApiMafraResponse, List<Garden>> {

    @Override
    public List<Garden> process(ApiMafraResponse response) throws Exception {
        if (response == null || response.getRow() == null) return List.of();

        return response.getRow().stream()
                .map(row -> Garden.builder()

                        .name(row.getFARM_NM())
                        .type(getType(row.getFARM_TYPE()))
                        .address(row.getADDRESS1())
                        .link(row.getHOMEPAGE())

                        .build())

                .filter(garden -> garden.getName() != null && !garden.getName().isEmpty())
                .collect(Collectors.toList());
    }

    private GardenType getType(String type) {
        if (type.equals("민간단체")) return GardenType.PRIVATE;

        return GardenType.UNKNOWN;
    }

}
