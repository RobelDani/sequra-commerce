package com.sequra.commerce.repository;

import com.sequra.commerce.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o from Orders o " +
            "WHERE o.completedAt IS NOT NULL " +
            "AND NOT EXISTS (SELECT d FROM Disbursements d " +
            "WHERE o.id = d.orderId)")
    List<Orders> findCompleteOrders();
}
