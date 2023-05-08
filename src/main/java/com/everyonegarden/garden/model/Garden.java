package com.everyonegarden.garden.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "garden")
public class Garden {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenId;

    private double latitude;
    private double longitude;
    private String address;

    @Column(nullable = false) @NotBlank
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GardenType type;

    private String link;
    private Integer price;

    private String contact;
    private String size;

    private LocalDateTime recruitStartDate;
    private LocalDateTime recruitEndDate;
    private LocalDateTime useStartDate;
    private LocalDateTime useEndDate;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}