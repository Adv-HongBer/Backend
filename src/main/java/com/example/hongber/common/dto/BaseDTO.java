package com.example.hongber.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDTO {
    private String useYn;
    private LocalDateTime regDt;
    private Long regIdx;
    private LocalDateTime updDt;
    private Long updIdx;
}
