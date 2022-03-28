package com.sequra.commerce.converter;

import com.sequra.commerce.response.MerchantsRS;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sequra.commerce.EngineTestBeans.DISBURSEMENTS;
import static org.assertj.core.api.Assertions.assertThat;

class DisbursementsToMerchantsRSConverterTest {

    private final DisbursementsToMerchantsRSConverter disbursementsToMerchantsRSConverter = new DisbursementsToMerchantsRSConverter();

    @Test
    void convert() {
        final List<MerchantsRS> merchantsRSList = disbursementsToMerchantsRSConverter.convert(List.of(DISBURSEMENTS, DISBURSEMENTS));

        assertThat(merchantsRSList).isNotEmpty()
                .hasSize(2);
    }
}