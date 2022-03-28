package com.sequra.commerce.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DisbursementsRQ implements Serializable {

    private static final long serialVersionUID = -3758207191767154789L;

    Long merchantId;
    @NotNull
    @PastOrPresent(message = "The date must be before or equal to the current day.")
    LocalDate startDate;
    @NotNull
    LocalDate endDate;

    @JsonCreator
    public DisbursementsRQ(@JsonProperty("merchantId") final Long merchantId, @JsonProperty("startDate") final LocalDate startDate,
                           @JsonProperty("endDate") final LocalDate endDate) {
        this.merchantId = merchantId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
