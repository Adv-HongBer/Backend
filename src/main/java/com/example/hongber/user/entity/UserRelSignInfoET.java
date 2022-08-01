package com.example.hongber.user.entity;

import com.example.hongber.common.constant.ConstDateFormat;
import com.example.hongber.common.util.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "userRelSignInfo")
public class UserRelSignInfoET {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    private Long userIdx;
    private String reSignDt;
    private String lastSignInDt;
    private String changePassDt;

    public void saveReSign(Long userIdx) {
        this.userIdx = userIdx;
        this.reSignDt = DateUtil.getNowDate(ConstDateFormat.YYYY_MM_DD_HH_MM_SS);
    }

    public void saveLastSignIn(Long userIdx) {
        this.userIdx = userIdx;
        this.lastSignInDt = DateUtil.getNowDate(ConstDateFormat.YYYY_MM_DD_HH_MM_SS);
    }

    public void saveChangePass(Long userIdx) {
        this.userIdx = userIdx;
        this.changePassDt = DateUtil.getNowDate(ConstDateFormat.YYYY_MM_DD_HH_MM_SS);
    }
}
