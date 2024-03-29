package com.everyonegarden.garden.gardenUsing;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "GARDEN_USING")
@Entity
public class GardenUsing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;
    private Double latitude;
    private Double longitude;

    private LocalDate useStartDate;
    private LocalDate useEndDate;

    private Long memberId;

    private String image;

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
