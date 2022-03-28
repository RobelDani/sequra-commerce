package com.sequra.commerce.service;

import com.sequra.commerce.converter.DisbursementsToMerchantsRSConverter;
import com.sequra.commerce.entity.Disbursements;
import com.sequra.commerce.entity.Orders;
import com.sequra.commerce.repository.DisbursementsRepository;
import com.sequra.commerce.request.DisbursementsRQ;
import com.sequra.commerce.response.DisbursementsRS;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DisbursementsService {

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    private final DisbursementsRepository disbursementsRepository;
    private final OrdersService ordersService;
    private final DisbursementsToMerchantsRSConverter disbursementsToDisbursementsRSConverter;

    public DisbursementsService(final DisbursementsRepository disbursementsRepository,
                                final OrdersService ordersService,
                                final DisbursementsToMerchantsRSConverter disbursementsToDisbursementsRSConverter) {
        this.disbursementsRepository = disbursementsRepository;
        this.ordersService = ordersService;
        this.disbursementsToDisbursementsRSConverter = disbursementsToDisbursementsRSConverter;
    }

    public DisbursementsRS findDisbursements(final DisbursementsRQ disbursementsRQ) {

        final List<Disbursements> disbursements = Objects.nonNull(disbursementsRQ.getMerchantId())
                ? disbursementsRepository.findByMerchantIdAndStartWeekGreaterThanEqualAndEndWeekLessThanEqual(
                disbursementsRQ.getMerchantId(), disbursementsRQ.getStartDate(), disbursementsRQ.getEndDate())
                : disbursementsRepository.findByStartWeekGreaterThanEqualAndEndWeekLessThanEqual(
                disbursementsRQ.getStartDate(), disbursementsRQ.getEndDate());

        return new DisbursementsRS(disbursementsToDisbursementsRSConverter.convert(disbursements));
    }

    @Scheduled(cron = "0 0 23 ? * MON")
    public void calculateDisbursements() {
        final List<Orders> completeOrders = ordersService.findCompleteOrders();

        final List<Disbursements> disbursements = completeOrders.stream()
                .collect(groupingBy(Orders::getMerchantId))
                .values().stream()
                .map(this::getDisbursements)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        disbursementsRepository.saveAll(disbursements);
    }

    private List<Disbursements> getDisbursements(final List<Orders> ordersGroupByMerchant) {
        return ordersGroupByMerchant.stream()
                .sorted(Comparator.comparing(Orders::getCompletedAt))
                .collect(groupingBy(or -> calculateInitWeek(or.getCompletedAt())))
                .values().stream()
                .map(this::createDisbursements)
                .collect(Collectors.toList());
    }

    private Disbursements createDisbursements(final List<Orders> ordersGroupByInitWeek) {
        final BigDecimal disbursementByWeek = ordersGroupByInitWeek.stream()
                .map(orders -> calculateAmount(orders.getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        final Orders order = ordersGroupByInitWeek.get(0);
        return Disbursements.builder()
                .merchantId(order.getMerchantId())
                .orderId(order.getId())
                .startWeek(calculateInitWeek(order.getCompletedAt()))
                .endWeek(calculateEndWeek(order.getCompletedAt()))
                .disbursement(disbursementByWeek)
                .build();
    }

    private BigDecimal calculateAmount(final BigDecimal amount) {
        return amount.compareTo(BigDecimal.valueOf(50)) < 0
                ? amount.add(amount.multiply(BigDecimal.valueOf(0.01)))
                : overFifty(amount);
    }

    private BigDecimal overFifty(final BigDecimal amount) {
        return amount.compareTo(BigDecimal.valueOf(50)) >= 0 && amount.compareTo(BigDecimal.valueOf(300)) <= 0
                ? amount.add(amount.multiply(BigDecimal.valueOf(0.0095)))
                : amount.add(amount.multiply(BigDecimal.valueOf(0.0085)));
    }

    private LocalDate calculateInitWeek(final LocalDateTime completeAt) {
        final Date date = getDate(completeAt);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

        return calendar.getTime().toInstant().atZone(DEFAULT_ZONE_ID).toLocalDate();
    }

    private LocalDate calculateEndWeek(final LocalDateTime completeAt) {
        final Date date = getDate(completeAt);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        return calendar.getTime().toInstant().atZone(DEFAULT_ZONE_ID).toLocalDate();
    }

    private Date getDate(final LocalDateTime completeAt) {
        final ZonedDateTime zonedDateTime = completeAt.atZone(DEFAULT_ZONE_ID);
        return Date.from(zonedDateTime.toInstant());
    }
}
