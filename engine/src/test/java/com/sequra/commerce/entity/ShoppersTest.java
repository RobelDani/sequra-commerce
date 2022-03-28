package com.sequra.commerce.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

import static com.sequra.commerce.EngineTestBeans.SHOPPERS;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = JacksonAutoConfiguration.class)
@JsonTest
class ShoppersTest {
    //language=JSON
    private static final String JSON_OBJECT = "{\n" +
            "  \"id\": 1,\n" +
            "  \"name\": \"name\",\n" +
            "  \"email\": \"email@email.com\",\n" +
            "  \"nif\": \"nif\"\n" +
            "}";

    private static final Shoppers EXPECTED_OBJECT = SHOPPERS;

    @Autowired
    private JacksonTester<Shoppers> jacksonTester;

    @Test
    void shouldSerialize() throws IOException {
        assertThat(this.jacksonTester.write(EXPECTED_OBJECT)).isEqualToJson(JSON_OBJECT);
    }

    @Test
    void shouldDeserialize() throws IOException {
        assertThat(this.jacksonTester.parse(JSON_OBJECT).getObject()).isEqualTo(EXPECTED_OBJECT);
    }
}