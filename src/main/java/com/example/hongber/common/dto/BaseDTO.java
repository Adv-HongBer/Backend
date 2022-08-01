package com.example.hongber.common.dto;

import com.example.hongber.common.constant.ConstDateFormat;
import com.example.hongber.common.util.DateUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDTO {
    private String useYn;
    private LocalDateTime regDt;
    private Long regIdx;
    private LocalDateTime updDt;
    private Long updIdx;

    public BaseDTO() {
        this.regDt = LocalDateTime.parse(DateUtil.getNowDate(ConstDateFormat.YYYY_MM_DD_T_HH_MM_SS));
        this.updDt = LocalDateTime.parse(DateUtil.getNowDate(ConstDateFormat.YYYY_MM_DD_T_HH_MM_SS));
    }
}
