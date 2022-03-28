package com.sequra.commerce.repository;

import com.sequra.commerce.entity.Disbursements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DisbursementsRepository extends JpaRepository<Disbursements, Long> {

    List<Disbursements> findByMerchantIdAndStartWeekGreaterThanEqualAndEndWeekLessThanEqual(Long merchantId, LocalDate startWeek, LocalDate endWeek);

    List<Disbursements> findByStartWeekGreaterThanEqualAndEndWeekLessThanEqual(LocalDate startWeek, LocalDate endWeek);
}
