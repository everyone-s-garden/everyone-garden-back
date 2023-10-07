package com.everyonegarden.garden.garden;

import com.everyonegarden.garden.gardenImage.GardenImage;
import com.everyonegarden.garden.gardenLike.model.GardenLike;
import com.everyonegarden.garden.gardenView.GardenView;
import com.everyonegarden.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "garden")
public class Garden {

    private final int DELETED_MAX_SCORE = 25;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenId;

    private String address;
    private Double latitude;
    private Double longitude;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GardenType type;
    @Enumerated(value = EnumType.STRING)
    @Column(length = 10)
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
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    private Boolean toilet;
    private Boolean waterway;
    private Boolean equipment;

    @OneToMany(mappedBy = "garden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GardenImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "garden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GardenView> gardenViews = new ArrayList<>();

    @OneToMany(mappedBy = "garden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GardenLike> gardenLikes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    private boolean deleted;
    private int reportedScore;

    public Garden editGarden(Garden editedGarden) {
        if (editedGarden.getAddress() != null) address = editedGarden.getAddress();
        if (editedGarden.getName() != null) name = editedGarden.getName();
        if (editedGarden.getLink() != null) link = editedGarden.getLink();
        if (editedGarden.getPrice() != null) price = editedGarden.getPrice();
        if (editedGarden.getSize() != null) size = editedGarden.getSize();
        if (editedGarden.getContact() != null) contact = editedGarden.getContact();
        if (editedGarden.getToilet() != null) toilet = editedGarden.getToilet();
        if (editedGarden.getWaterway() != null) waterway = editedGarden.getWaterway();
        if (editedGarden.getEquipment() != null) equipment = editedGarden.getEquipment();
        if (editedGarden.getContent() != null) content = editedGarden.getContent();
        if (editedGarden.getStatus() != null) status = editedGarden.getStatus();

        return this;
    }

    public void addImage(GardenImage image) {
        if (images == null) images = new ArrayList<>();

        images.add(image);
        image.setGarden(this);
    }

    public void addGardenImages(List<GardenImage> images) {
        this.images.addAll(images);
    }

    public void removeImage(GardenImage image) {
        this.images.remove(image);
    }

    public void changeDelete() {
        deleted = true;
    }

    public void registerReport(int score) {
        reportedScore += score;
        if (reportedScore > DELETED_MAX_SCORE) {
            changeDelete();
        }
    }

}
