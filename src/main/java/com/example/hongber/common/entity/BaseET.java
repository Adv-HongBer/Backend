package com.example.hongber.common.entity;

import com.example.hongber.common.constant.ConstDateFormat;
import com.example.hongber.common.constant.ConstUseYn;
import com.example.hongber.common.util.common.DateUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamicInsert
@DynamicUpdate
public abstract class BaseET {
    @Column(updatable = false)
    @ColumnDefault(ConstUseYn.USE_Y)
    private String useYn;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime regDt;

    @Column(updatable = false)
    private Long regIdx;

    @LastModifiedDate
    private LocalDateTime updDt;

    private Long updIdx;

    public void setUpd(Long userIdx) {
        this.updDt = LocalDateTime.parse(DateUtil.getNowDate(ConstDateFormat.YYYY_MM_DD_HH_MM_SS));
        this.updIdx = userIdx;
    }

    public void setDel(Long userIdx) {
        this.updDt = LocalDateTime.parse(DateUtil.getNowDate(ConstDateFormat.YYYY_MM_DD_HH_MM_SS));
        this.updIdx = userIdx;
        this.useYn = "N";
    }
}
