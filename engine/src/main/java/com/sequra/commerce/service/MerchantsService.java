package com.sequra.commerce.service;

import com.sequra.commerce.entity.Merchants;
import com.sequra.commerce.repository.MerchantsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantsService {

    private final MerchantsRepository merchantsRepository;

    public MerchantsService(final MerchantsRepository merchantsRepository) {
        this.merchantsRepository = merchantsRepository;
    }

    public void save(final List<Merchants> merchants) {
        merchantsRepository.saveAll(merchants);
    }
}
