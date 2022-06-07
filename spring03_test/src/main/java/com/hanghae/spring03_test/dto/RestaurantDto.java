package com.hanghae.spring03_test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class RestaurantDto {
    public Long id;
    public String name;
    public int minOrderPrice;
    public int deliveryFee;

    public RestaurantDto(Long id, String name, int minOrderPrice, int deliveryFee){
        this.id = id;
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }
}
