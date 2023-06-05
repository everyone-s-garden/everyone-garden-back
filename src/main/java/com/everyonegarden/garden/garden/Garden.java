package com.everyonegarden.garden.garden;

import com.everyonegarden.garden.gardenImage.GardenImage;
import com.everyonegarden.member.entity.Member;
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
import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@EntityListeners(AuditingEntityListener.class)
@Entity @Table(name = "garden")
public class Garden {

    @Column(name = "id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenId;

    private String address;
    private double latitude;
    private double longitude;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private GardenType type;
    @Enumerated(value = EnumType.STRING) @Column(length = 10)
    private GardenStatus status;

    private String link;
    private String price;

    private String contact;
    private String size;

    @Column(length = 1000)
    private String content;

    private LocalDateTime recruitStartDate;
    private LocalDateTime recruitEndDate;
    private LocalDateTime useStartDate;
    private LocalDateTime useEndDate;
    @CreatedDate private LocalDateTime createdDate;
    @LastModifiedDate private LocalDateTime lastModifiedDate;

    private Boolean toilet;
    private Boolean waterway;
    private Boolean equipment;

    @OneToMany(mappedBy = "garden")
    private List<GardenImage> images;

    @ManyToOne @JoinColumn(name = "member_id")
    private Member member;

    public Garden editGarden(Garden editedGarden) {
        address = editedGarden.getAddress();
        name = editedGarden.getName();
        link = editedGarden.getLink();
        price = editedGarden.getPrice();
        size = editedGarden.getSize();
        contact = editedGarden.getContact();
        content = editedGarden.getContent();
        toilet = editedGarden.getToilet();
        waterway = editedGarden.getWaterway();
        equipment = editedGarden.getEquipment();

        return this;
    }

    public Garden editGardenIgnoreNull(Garden editedGarden) {
        if (editedGarden.getAddress() != null) address = editedGarden.getAddress();
        if (editedGarden.getName() != null) name = editedGarden.getName();
        if (editedGarden.getLink() != null) link = editedGarden.getLink();
        if (editedGarden.getPrice() != null) price = editedGarden.getPrice();
        if (editedGarden.getSize() != null) size = editedGarden.getSize();
        if (editedGarden.getContact() != null) contact = editedGarden.getContact();
        if (editedGarden.getToilet() != null) toilet = editedGarden.getToilet();
        if (editedGarden.getWaterway() != null) waterway = editedGarden.getWaterway();
        if (editedGarden.getEquipment() != null) equipment = editedGarden.getEquipment();

        return this;
    }

}