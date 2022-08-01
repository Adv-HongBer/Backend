package com.example.hongber.user.entity;

import com.example.hongber.common.entity.BaseET;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

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
@SQLDelete(sql = "UPDATE user SET useYn = 'N' WHERE idx = ?")
@Table(name = "user")
public class UserET extends BaseET {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    private String userId;
    private String pass;
    private String tel;
    private String email;
    private String userNm;
    private String nickNm;
    private Long userTypeIdx;
    private Long userStatusIdx;
}
