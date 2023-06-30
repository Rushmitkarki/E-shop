package com.ecommerce.ecommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Review {

    @Id
    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item item;

    @Id
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}
