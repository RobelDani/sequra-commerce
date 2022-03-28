package com.sequra.commerce.converter;

import com.sequra.commerce.entity.Disbursements;
import com.sequra.commerce.response.MerchantsRS;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DisbursementsToMerchantsRSConverter implements Converter<List<Disbursements>, List<MerchantsRS>> {

    @Override
    public List<MerchantsRS> convert(final List<Disbursements> disbursements) {
        return disbursements.stream()
                .map(this::createMerchantsRS)
                .collect(Collectors.toList());
    }

    private MerchantsRS createMerchantsRS(final Disbursements disbursement) {
        return MerchantsRS.builder()
                .merchantId(disbursement.getMerchantId())
                .startWeek(disbursement.getStartWeek())
                .endWeek(disbursement.getEndWeek())
                .disbursement(disbursement.getDisbursement())
                .build();
    }
}
