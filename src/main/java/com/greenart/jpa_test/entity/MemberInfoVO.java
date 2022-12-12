package com.greenart.jpa_test.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "member_info") // 연결 될 테이블 설정
public class MemberInfoVO {
    //mi_seq;mi_id;mi_pwd;mi_name;mi_nickname;mi_reg_dt;mi_status
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue(strategy = GenerationType.IDENTITY) -> MySQL의 AUTO_INCREMENT 다.
    // @Id 는 jpa 즉 , javax.persistance.Id 껄로 사용한다.
    @Column(name = "mi_seq")    private Integer seq;
    @Column(name = "mi_id")    private String id;
    @Column(name = "mi_pwd")    private String pwd;
    @Column(name = "mi_name")    private String name;
    @Column(name = "mi_nickname")    private String nickname;
    @Column(name = "mi_reg_dt")    private Date regDt;
    @Column(name = "mi_status")    private Integer status;
}
