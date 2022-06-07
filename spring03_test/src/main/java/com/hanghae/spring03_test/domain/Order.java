package com.hanghae.spring03_test.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(
        name = "ORDER_A",
        sequenceName = "ORDER_B",
        initialValue = 1, allocationSize = 50)
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_A")
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="RESTAURANT_ID")
    private Restaurant restaurant;

    @Column()
    private int totalPrice;

    @JsonBackReference
    @OneToMany(mappedBy = "order")
    //@JoinColumn(name = "ORDERFOOD_ID")
    private List<OrderFood> orderFoods = new ArrayList<>();

    //주문??
    public Order(Restaurant restaurant, int totalPrice, List<OrderFood> orderFoods){
        this.restaurant = restaurant;
        this.totalPrice = totalPrice;
        this.orderFoods = orderFoods;
    }

    public void addOrderFoods(OrderFood orderFood){
        this.orderFoods.add(orderFood);
    }
}

