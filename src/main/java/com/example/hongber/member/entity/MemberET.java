package com.example.hongber.member.entity;

import com.example.hongber.common.annotation.Encrypt;
import com.example.hongber.common.entity.BaseET;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@SQLDelete(sql = "UPDATE member SET useYn = 'N' WHERE idx = ?")
@Table(name = "member")
public class MemberET extends BaseET {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Encrypt
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String memberId;
    @Encrypt
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pass;
    @Encrypt
    private String tel;
    @Encrypt
    private String email;
    @Encrypt
    private String memberNm;
    @Encrypt
    private String nickNm;
}