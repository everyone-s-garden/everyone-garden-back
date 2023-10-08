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
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

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
        isValidatedAddress(editedGarden.getAddress());
        isValidatedName(editedGarden.getName());
        isValidatedName(editedGarden.getLink());
        isValidatedPrice(editedGarden.getPrice());
        isValidatedSize(editedGarden.getSize());
        Assert.notNull(editedGarden.getToilet(), "게시글에 화장실 제공 여부는 필수값입니다.");
        Assert.notNull(editedGarden.getWaterway(), "게시글에 수로 제공 여부는 필수값입니다.");
        Assert.notNull(editedGarden.getEquipment(), "게시글에 농사 장비 제공 여부는 필수값입니다.");
        isValidatedContent(editedGarden.getContent());
        Assert.notNull(editedGarden.getStatus(), "분양 텃밭의 거래 상태 여부는 필수값입니다.");

        address = editedGarden.getAddress();
        name = editedGarden.getName();
        link = editedGarden.getLink();
        price = editedGarden.getPrice();
        size = editedGarden.getSize();
        contact = editedGarden.getContact();
        toilet = editedGarden.getToilet();
        waterway = editedGarden.getWaterway();
        equipment = editedGarden.getEquipment();
        content = editedGarden.getContent();
        status = editedGarden.getStatus();

        return this;
    }

    public Garden(String address, Double latitude, Double longitude,
                  String name, GardenType type, GardenStatus status,
                  String link, String price, String contact,
                  String size, String content, Boolean toilet,
                  Boolean waterway, Boolean equipment,
                  Member member, boolean deleted, int reportedScore) {

        isValidatedAddress(address);
        isValidatedName(name);
        isValidatedLink(link);
        isValidatedPrice(price);
        isValidatedSize(size);
        Assert.notNull(toilet, "게시글에 화장실 제공 여부는 필수값입니다.");
        Assert.notNull(waterway, "게시글에 수로 제공 여부는 필수값입니다.");
        Assert.notNull(equipment, "게시글에 농사 장비 제공 여부는 필수값입니다.");
        isValidatedContent(content);
        isValidatedContact(contact);
        Assert.notNull(status, "분양 텃밭의 거래 상태 여부는 필수값입니다.");
        Assert.notNull(type, "분양 텃밭의 공급 주체는 필수값입니다.");
        Assert.notNull(latitude, "분양 텃밭의 위도는 null일 수 없습니다.");
        Assert.notNull(longitude, "분양 텃밭의 경도는 null일 수 없습니다.");

        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.type = type;
        this.status = status;
        this.link = link;
        this.price = price;
        this.contact = contact;
        this.size = size;
        this.content = content;
        this.toilet = toilet;
        this.waterway = waterway;
        this.equipment = equipment;
        this.member = member;
        this.deleted = deleted;
        this.reportedScore = reportedScore;
    }

    public void isValidatedAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("텃밭의 주소는 null이거나 빈 값이면 안됩니다.");
        }
    }

    public void isValidatedName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("텃밭의 이름은 null이거나 빈 값이면 안됩니다.");
        }
    }

    public void isValidatedLink(String link) {
        if (link == null || link.isBlank()) {
            throw new IllegalArgumentException("텃밭 신청 링크는 null이거나 빈 값이면 안됩니다.");
        }
    }

    public void isValidatedContact(String contact) {
        if (contact == null || contact.isBlank()) {
            throw new IllegalArgumentException("텃밭 신청 링크는 null이거나 빈 값이면 안됩니다.");
        }
    }

    public void isValidatedPrice(String price) {
        if (price == null || price.isBlank()) {
            throw new IllegalArgumentException("텃밭의 분양 가격은 null이거나 빈 값이면 안됩니다.");
        }
    }

    public void isValidatedContent(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("텃밭에 대한 설명 null이거나 빈 값이면 안됩니다.");
        }
    }

    public void isValidatedSize(String size) { //todo : size가 음수인 것을 어떻게 검증할 것인가? String이 맞을까?
        if (size == null || size.isBlank()) {
            throw new IllegalArgumentException("텃밭의 크기는 null이거나 빈 값이면 안됩니다.");
        }
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
