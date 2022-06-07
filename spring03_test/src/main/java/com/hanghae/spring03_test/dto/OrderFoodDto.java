package com.hanghae.spring03_test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderFoodDto {
    //foodId
    private Long id;

    //foodName
    private String name;

    //주문 갯수
    private int quantity;

    //주문 가격
    private int price;




}
