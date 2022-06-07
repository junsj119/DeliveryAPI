package com.hanghae.spring03_test.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(
        name = "ORDERFOOD_A",
        sequenceName = "ORDERFOOD_B",
        initialValue = 1, allocationSize = 50)
public class OrderFood {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERFOOD_A")
    @Column(name="ORDERFOOD_ID")
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="ORDER_ID", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="FOOD_ID", nullable = false)
    private Food food;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;


    public OrderFood(Food food, int quantity, int orderPrice) {
        this.food = food;
        this.quantity = quantity;
        this.price = orderPrice;
    }

}




