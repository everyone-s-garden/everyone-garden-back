package com.everyonegarden.weather.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    String regionName; // 지역 이름

}
