package com.hanghae.spring03_test.dto;

import com.hanghae.spring03_test.domain.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDto {
    private String restaurantName;
    private List<OrderFoodDto> foods;
    private int deliveryFee;
    private int totalPrice;

    public OrderResponseDto(Order order, List<OrderFoodDto> foods) {
        this.restaurantName = order.getRestaurant().getName();
        this.foods = foods;     //name, quantity, price
        this.deliveryFee = order.getRestaurant().getDeliveryFee();
        this.totalPrice = order.getTotalPrice();
    }
}
