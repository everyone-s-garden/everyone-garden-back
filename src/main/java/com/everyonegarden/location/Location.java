package com.everyonegarden.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Table
@Entity
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

    public String getFullAddress() {
        StringBuilder levelCombined = new StringBuilder();
        if (level1 != null) levelCombined.append(level1).append(" ");
        if (level2 != null) levelCombined.append(level2).append(" ");
        if (level3 != null) levelCombined.append(level3).append(" ");
        if (level4 != null) levelCombined.append(level4).append(" ");
        return levelCombined.toString();
    }
}
