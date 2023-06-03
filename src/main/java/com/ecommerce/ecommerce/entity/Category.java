package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

@Table
@Entity
public class Category {
    @Id
    @SequenceGenerator(name = "cat_seq_generator",sequenceName ="cat_id_seq",allocationSize  =1)
    @GeneratedValue(generator = "cat_seq_generator",strategy=GenerationType.SEQUENCE)
    private Integer catId;
}
