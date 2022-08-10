package com.example.hongber.user.entity;

import com.example.hongber.common.annotation.Encrypt;
import com.example.hongber.common.entity.BaseET;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE user SET useYn = 'N' WHERE idx = ?")
@Table(name = "user")
public class UserET extends BaseET {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Encrypt
    private String userId;
    @Encrypt
    private String pass;
    @Encrypt
    private String tel;
    @Encrypt
    private String email;
    @Encrypt
    private String userNm;
    @Encrypt
    private String nickNm;
    private Long userTypeIdx;
    private Long userStatusIdx;
}