package com.everyonegarden.garden.gardenUsing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@Entity @Table
public class GardenUsing {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;

    private String address;
    private Double latitude;
    private Double longitude;

    private LocalDate useStartDate;
    private LocalDate useEndDate;

    private Long memberId;

    public GardenUsing edit(GardenUsing editedGardenUsing) {

        if (editedGardenUsing.getName() != null) {
            name = editedGardenUsing.getName();
        }

        if (editedGardenUsing.getImage() != null) {
            image = editedGardenUsing.getImage();
        }

        if (editedGardenUsing.getAddress() != null) {
            address = editedGardenUsing.getAddress();
        }

        if (editedGardenUsing.getLatitude() != null) {
            latitude = editedGardenUsing.getLatitude();
        }

        if (editedGardenUsing.getLongitude() != null) {
            longitude = editedGardenUsing.getLongitude();
        }

        if (editedGardenUsing.getUseStartDate() != null) {
            useStartDate = editedGardenUsing.getUseStartDate();
        }

        if (editedGardenUsing.getUseEndDate() != null) {
            useEndDate = editedGardenUsing.getUseEndDate();
        }

        return this;

    }

}