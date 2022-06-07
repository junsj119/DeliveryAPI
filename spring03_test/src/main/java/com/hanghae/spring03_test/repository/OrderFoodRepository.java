package com.hanghae.spring03_test.repository;

import com.hanghae.spring03_test.domain.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
}
