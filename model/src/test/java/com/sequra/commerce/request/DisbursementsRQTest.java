package com.sequra.commerce.request;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = JacksonAutoConfiguration.class)
@JsonTest
class DisbursementsRQTest {
    //language=JSON
    private static final String JSON_OBJECT = "{\n" +
            "  \"merchantId\": 1,\n" +
            "  \"startDate\": \"2018-10-12\",\n" +
            "  \"endDate\": \"2018-10-19\"\n" +
            "}";

    private static final DisbursementsRQ EXPECTED_OBJECT = new DisbursementsRQ(1L,
            LocalDate.of(2018, 10, 12), LocalDate.of(2018, 10, 19));

    @Autowired
    private JacksonTester<DisbursementsRQ> jacksonTester;

    @Test
    void shouldSerialize() throws IOException {
        assertThat(this.jacksonTester.write(EXPECTED_OBJECT)).isEqualToJson(JSON_OBJECT);
    }
}