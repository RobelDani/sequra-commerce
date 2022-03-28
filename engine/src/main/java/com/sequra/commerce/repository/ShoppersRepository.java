package com.sequra.commerce.repository;

import com.sequra.commerce.entity.Shoppers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppersRepository extends JpaRepository<Shoppers, Long> {

}
