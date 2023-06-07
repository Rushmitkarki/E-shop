package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Category {
    @Id
    @SequenceGenerator(name = "cat_seq_generator",sequenceName ="cat_id_seq",allocationSize  =1)
    @GeneratedValue(generator = "cat_seq_generator",strategy=GenerationType.SEQUENCE)
    private Integer catId;
    @Column(name="cat_name", length=50, nullable=false)
    private String catName;
}
