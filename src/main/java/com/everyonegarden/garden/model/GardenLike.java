package com.everyonegarden.garden.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@Table
@Entity
public class GardenLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenLikeId;



}
