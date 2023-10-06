package com.everyonegarden.region.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="region_id")
    private Long id;

    @Column
    String nx ;

    @Column
    String ny ;

    @Column
    String regionId;

    @Column
    String regionName;

    public Region(Long id, String nx, String ny, String regionId, String regionName) {
        this.id = id;
        this.nx = nx;
        this.ny = ny;
        this.regionId = regionId;
        this.regionName = regionName;
    }

    public static String formatRegionName(String lng, String lat) {
        return lng + "," + lat;
    }

    public String makeRegionNameResponse( ) {
        if (regionName.length() == 4) {
            return regionName.substring(0, 1) + regionName.substring(2, 3);
        }

        return regionName.substring(0, 2);
    }

}
