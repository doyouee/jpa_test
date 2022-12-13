package com.greenart.jpa_test.product.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product_info")
@DynamicInsert //default값 넣어주기 (insert into할때 null값 들어오는 것 무시하고 default 값 넣음)
@DynamicUpdate // 입력 안한거 무시한다(?)
@Builder // UpdateMapping때 사용
public class ProductInfoVO {
    //pi_seq;pi_name;pi_price;pi_discount;pi_reg_dt;pi_status;pi_stock
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY = auto_increment
    @Column(name = "pi_seq")        private Integer seq;
    @Column(name = "pi_name")       private String name;
    @Column(name = "pi_price")      private Integer price; //private Integer price = 0; 이렇게 초기화해도 되지만 수정이 어려우니 위에 DynamicInsert를 집어넣어줌
    @Column(name = "pi_discount")   private Double discount;
    @Column(name = "pi_reg_dt")     private Date regDt;
    @Column(name = "pi_status")     private Integer status;
    @Column(name = "pi_stock")      private Integer stock;
}
