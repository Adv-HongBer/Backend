package com.example.hongber.common.entity;

import com.example.hongber.common.constant.ConstDateFormat;
import com.example.hongber.common.util.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Column(updatable = false)
    @ColumnDefault("Y")
    private String useYn;

    @Column(updatable = false)
    @CreatedDate
    private String regDt;

    @Column(updatable = false)
    private Long regIdx;

    @LastModifiedDate
    private String updDt;

    private Long updIdx;

    public void setUpd(Long userIdx) {
        this.updDt = DateUtil.getNowDate(ConstDateFormat.YYYY_MM_DD_HH_MM_SS);
        this.updIdx = userIdx;
    }

    public void setDel(Long userIdx) {
        this.updDt = DateUtil.getNowDate(ConstDateFormat.YYYY_MM_DD_HH_MM_SS);
        this.updIdx = userIdx;
        this.useYn = "N";
    }
}
