package com.hanghae.spring03_test.repository;

import com.hanghae.spring03_test.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
