package com.hanghae.spring03_test.repository;

import com.hanghae.spring03_test.domain.Food;
import com.hanghae.spring03_test.domain.Restaurant;
import com.hanghae.spring03_test.dto.FoodDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByRestaurantId(Long restaurantId);
    //Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);
}
