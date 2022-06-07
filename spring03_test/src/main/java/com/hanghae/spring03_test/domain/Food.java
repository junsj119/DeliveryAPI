package com.hanghae.spring03_test.domain;

import com.hanghae.spring03_test.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        uniqueConstraints={
                @UniqueConstraint( name="UNIQUE_FOOD",
                        columnNames = {"RESTAURANT_ID", "name"}
                )
        }
)

@SequenceGenerator(
        name = "FOOD_A",
        sequenceName = "FOOD_B",
        initialValue = 1, allocationSize = 50)
public class Food {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "FOOD_A")
    @Column(name = "FOOD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    //음식 등록할 때 사용한다.
    public Food(Restaurant restaurant, FoodDto foodDto){
        this.restaurant = restaurant;
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
    }
}
