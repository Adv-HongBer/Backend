package com.example.hongber.menu.entity;

import com.example.hongber.common.entity.BaseET;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

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
@Where(clause="useYn = 'Y'")
@DynamicInsert
@DynamicUpdate
@Table(name = "menuDtl")
public class MenuDtlET extends BaseET {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    private Long menuMaIdx;
    private String menuDtlNm;
    private String menuDtlLink;
    private Long menuDtlOrder;
    private Long menuDtlAccessLv;
}
