package com.sequra.commerce.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DisbursementsRS implements Serializable {

    private static final long serialVersionUID = -2785773598923352636L;

    @Singular
    private final List<MerchantsRS> merchantsRS;

    @JsonCreator
    public DisbursementsRS(@JsonProperty("merchantsRS") final List<MerchantsRS> merchantsRS) {
        this.merchantsRS = merchantsRS;
    }
}
