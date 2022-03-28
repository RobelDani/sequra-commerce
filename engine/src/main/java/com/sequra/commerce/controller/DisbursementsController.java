package com.sequra.commerce.controller;

import com.sequra.commerce.request.DisbursementsRQ;
import com.sequra.commerce.response.DisbursementsRS;
import com.sequra.commerce.service.DisbursementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/disbursements")
@RequiredArgsConstructor
public class DisbursementsController {

    private final DisbursementsService disbursementsService;

    @PostMapping
    public DisbursementsRS getDisbursements(@Valid @RequestBody final DisbursementsRQ disbursementsRQ) {
        return this.disbursementsService.findDisbursements(disbursementsRQ);
    }
}
