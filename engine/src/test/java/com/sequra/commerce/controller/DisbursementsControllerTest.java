package com.sequra.commerce.controller;

import com.sequra.commerce.service.DisbursementsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.sequra.commerce.EngineTestBeans.DISBURSEMENTS_RQ;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class DisbursementsControllerTest {

    private DisbursementsController disbursementsController;

    @BeforeEach
    void setUp() {
        final DisbursementsService disbursementsService = mock(DisbursementsService.class);
        this.disbursementsController = new DisbursementsController(disbursementsService);
    }

    @Test
    void getDisbursements() {
        assertThat(this.disbursementsController.getDisbursements(DISBURSEMENTS_RQ)).isNull();
    }
}