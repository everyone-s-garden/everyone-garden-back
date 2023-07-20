package com.everyonegarden.main;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class MainResponse {

    @DateTimeFormat(pattern = "yyyy-MM-DDThh:mm:ss")
    private LocalDateTime timestamp;

    private Integer status;
    private String message;

}