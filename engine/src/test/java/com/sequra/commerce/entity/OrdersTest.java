package com.sequra.commerce.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

import static com.sequra.commerce.EngineTestBeans.ORDERS;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = JacksonAutoConfiguration.class)
@JsonTest
class OrdersTest {
    //language=JSON
    private static final String JSON_OBJECT = "{\n" +
            "  \"id\": 1,\n" +
            "  \"merchant_id\": 1,\n" +
            "  \"shopper_id\": 1,\n" +
            "  \"amount\": 10,\n" +
            "  \"created_at\": \"12/10/2018 13:20:52\",\n" +
            "  \"completed_at\": \"19/10/2018 13:20:52\"\n" +
            "}";

    private static final Orders EXPECTED_OBJECT = ORDERS;

    @Autowired
    private JacksonTester<Orders> jacksonTester;

    @Test
    void shouldDeserialize() throws IOException {
        assertThat(this.jacksonTester.parse(JSON_OBJECT).getObject()).isEqualTo(EXPECTED_OBJECT);
    }
}