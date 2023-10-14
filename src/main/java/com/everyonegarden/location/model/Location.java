package com.everyonegarden.location.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;

    private String level1;
    private String level2;
    private String level3;
    private String level4;

   // @Column(columnDefinition = "VARCHAR(2048) NOT NULL, FULLTEXT KEY addressFulltext (fullAddress)")
    private String fullAddress;

    public String assembleFullAddress() {
        StringBuilder levelCombined = new StringBuilder();
        if (level1 != null) levelCombined.append(level1).append(" ");
        if (level2 != null) levelCombined.append(level2).append(" ");
        if (level3 != null) levelCombined.append(level3).append(" ");
        if (level4 != null) levelCombined.append(level4).append(" ");
        return levelCombined.toString();
    }

    public Location(Double latitude, Double longitude, String level1, String level2, String level3, String level4, String fullAddress) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.level4 = level4;
        this.fullAddress = fullAddress;
    }

}

