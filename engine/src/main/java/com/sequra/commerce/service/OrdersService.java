package com.sequra.commerce.service;

import com.sequra.commerce.entity.Orders;
import com.sequra.commerce.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(final OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public void save(final List<Orders> orders) {
        ordersRepository.saveAll(orders);
    }

    public List<Orders> findCompleteOrders() {
        return ordersRepository.findCompleteOrders();
    }
}
