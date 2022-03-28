package com.sequra.commerce;

import com.sequra.commerce.entity.Disbursements;
import com.sequra.commerce.entity.Merchants;
import com.sequra.commerce.entity.Orders;
import com.sequra.commerce.entity.Shoppers;
import com.sequra.commerce.request.DisbursementsRQ;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EngineTestBeans {

    public static final DisbursementsRQ DISBURSEMENTS_RQ = new DisbursementsRQ(1L,
            LocalDate.of(2018, 10, 12), LocalDate.of(2018, 10, 19));

    public static final Disbursements DISBURSEMENTS = Disbursements.builder()
            .id(1L)
            .merchantId(1L)
            .orderId(1L)
            .startWeek(LocalDate.of(2018, 10, 12))
            .endWeek(LocalDate.of(2018, 10, 19))
            .disbursement(BigDecimal.TEN)
            .build();

    public static final Merchants MERCHANTS = Merchants.builder()
            .id(1L)
            .name("name")
            .email("email@email.com")
            .cif("cif")
            .build();

    public static final Orders ORDERS = Orders.builder()
            .id(1L)
            .amount(BigDecimal.TEN)
            .createdAt(LocalDateTime.of(2018, 10, 12, 13, 20, 52))
            .completedAt(LocalDateTime.of(2018, 10, 19, 13, 20, 52))
            .merchantId(1L)
            .shopperId(1L)
            .build();

    public static final Shoppers SHOPPERS = Shoppers.builder()
            .id(1L)
            .name("name")
            .email("email@email.com")
            .nif("nif")
            .build();
}
