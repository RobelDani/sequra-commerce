package com.sequra.commerce.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "disbursements")
@Data
public class Disbursements implements Serializable {

    private static final long serialVersionUID = -5643444762776574596L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "merchant_id")
    Long merchantId;
    @Column(name = "order_id")
    Long orderId;
    @Column(name = "disbursement")
    BigDecimal disbursement;
    @Column(name = "start_week")
    LocalDate startWeek;
    @Column(name = "end_week")
    LocalDate endWeek;
}
