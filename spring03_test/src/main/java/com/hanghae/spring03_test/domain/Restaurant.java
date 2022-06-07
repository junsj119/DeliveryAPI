package com.hanghae.spring03_test.domain;

import com.hanghae.spring03_test.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(
        name = "RESTAURANT_A",
        sequenceName = "RESTAURANT_B",
        initialValue = 1, allocationSize = 50)
public class Restaurant {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "RESTAURANT_A")

    @Column(name = "RESTAURANT_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    //음식점 추가할 때 사용한다.
    public Restaurant(String name, int minOrderPrice, int deliveryFee) {
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }
}
