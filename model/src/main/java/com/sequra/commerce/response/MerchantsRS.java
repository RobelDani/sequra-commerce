package com.sequra.commerce.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Jacksonized
public class MerchantsRS implements Serializable {

    private static final long serialVersionUID = 9153560843622296818L;

    Long merchantId;
    BigDecimal disbursement;
    LocalDate startWeek;
    LocalDate endWeek;
}
