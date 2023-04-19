package com.everyonegarden.garden.entity;

import com.everyonegarden.garden.enumerate.OperateType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Garden {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String latitude;
    private String longitute;
    private String address;
    private String link;
    private String name;//? 텃밭이름인가요?
    private String price; //String price의 기준은? 단위를 생각해볼것!
    private OperateType operateType; // 민간, 지자체, 개인 3가지 인데 enum으로 할것인지?
    private String contact;
    private String size;

    //작성자ID?도 없습니다.
    //Post와 오픈API를 분리하는 것은 어떤지?
    //생각해보니 사진이 없네요?

    //recruite_period어떻게 ?
    // 모집 시작일과 모집 끝나는 날

     //use_period 어떻게?
    //사용시작일과 사용지 끝나는 날

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime addDate;//created

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime editDate;//modified

    //batch날짜가 필요할까요?
    // 한달에 한번 돌리도록 하는데?



}
