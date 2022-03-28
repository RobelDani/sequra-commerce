package com.sequra.commerce.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

import static com.sequra.commerce.EngineTestBeans.DISBURSEMENTS;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = JacksonAutoConfiguration.class)
@JsonTest
class DisbursementsTest {
    //language=JSON
    private static final String JSON_OBJECT = "{\n" +
            "  \"id\": 1,\n" +
            "  \"merchantId\": 1,\n" +
            "  \"orderId\": 1,\n" +
            "  \"disbursement\": 10,\n" +
            "  \"startWeek\": \"2018-10-12\",\n" +
            "  \"endWeek\": \"2018-10-19\"\n" +
            "}";

    private static final Disbursements EXPECTED_OBJECT = DISBURSEMENTS;

    @Autowired
    private JacksonTester<Disbursements> jacksonTester;

    @Test
    void shouldSerialize() throws IOException {
        assertThat(this.jacksonTester.write(EXPECTED_OBJECT)).isEqualToJson(JSON_OBJECT);
    }

    @Test
    void shouldDeserialize() throws IOException {
        assertThat(this.jacksonTester.parse(JSON_OBJECT).getObject()).isEqualTo(EXPECTED_OBJECT);
    }
}