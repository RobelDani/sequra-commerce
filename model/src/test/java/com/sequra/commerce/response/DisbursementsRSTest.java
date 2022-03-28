package com.sequra.commerce.response;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = JacksonAutoConfiguration.class)
@JsonTest
class DisbursementsRSTest {
    //language=JSON
    private static final String JSON_OBJECT = "{\n" +
            "  \"merchantsRS\": [{\n" +
            "       \"merchantId\": 1,\n" +
            "       \"disbursement\": 10,\n" +
            "        \"startWeek\": \"2018-10-12\",\n" +
            "       \"endWeek\": \"2018-10-19\"\n" +
            "  }]\n" +
            "}";

    private static final DisbursementsRS EXPECTED_OBJECT = new DisbursementsRS(Collections.singletonList(MerchantsRS.builder()
            .merchantId(1L)
            .disbursement(BigDecimal.TEN)
            .startWeek(LocalDate.of(2018, 10, 12))
            .endWeek(LocalDate.of(2018, 10, 19)).build()));

    @Autowired
    private JacksonTester<DisbursementsRS> jacksonTester;

    @Test
    void shouldSerialize() throws IOException {
        assertThat(this.jacksonTester.write(EXPECTED_OBJECT)).isEqualToJson(JSON_OBJECT);
    }
}