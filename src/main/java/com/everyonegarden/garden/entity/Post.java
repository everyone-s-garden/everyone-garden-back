package com.everyonegarden.garden.entity;

import com.everyonegarden.user.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    private String latitude;
    private String longitute;
    private String address;
    private String name;//텃밭이름
    private String price; //String price의 기준은? 단위를 생각해볼것!

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperateType operateType; // 민간, 지자체, 개인 3가지 인데 enum으로 할것인지?
    private String contact;
    private String size;


    //연관관계 주인
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    @OneToMany(mappedBy = "post")
    private List<GardenPicture> gardenPicture = new ArrayList<>();


    // 모집 시작일
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime recruiteStartDate;
    // 모집 끝나는 날
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime recruiteEndDate;


    // 사용 시작일
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime useStartDate;
    // 사용지 끝나는 날
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime uesEndDate;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime addDate;//created

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime editDate;//modified
}
