package com.everyonegarden.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor @AllArgsConstructor

@Table @Entity
public class Location {

    @Id @GeneratedValue
    private Long regionId;

    private Double latitude;
    private Double longitude;

    private String level1;
    private String level2;
    private String level3;
    private String level4;

    public String getFullAddress() {
        String levelCombined = level1 + " " + level2 + " " + level3 + " " + level4;
        return levelCombined.strip();
    }

}
