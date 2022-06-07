package com.hanghae.spring03_test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long restaurantId;
    private List<OrderFoodDto> foods;
}
