package com.hanghae.spring03_test.repository;

import com.hanghae.spring03_test.domain.Order;
import com.hanghae.spring03_test.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Order> findByName(String name);
}
