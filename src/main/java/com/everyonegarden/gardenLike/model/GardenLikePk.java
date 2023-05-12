package com.everyonegarden.gardenLike.model;

import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode
public class GardenLikePk implements Serializable {


    private Long memberId;
    private Long gardenId;

}