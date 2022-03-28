package com.sequra.commerce.service;

import com.sequra.commerce.entity.Shoppers;
import com.sequra.commerce.repository.ShoppersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppersService {

    private final ShoppersRepository shoppersRepository;

    public ShoppersService(final ShoppersRepository shoppersRepository) {
        this.shoppersRepository = shoppersRepository;
    }

    public void save(final List<Shoppers> shoppers) {
        shoppersRepository.saveAll(shoppers);
    }
}
