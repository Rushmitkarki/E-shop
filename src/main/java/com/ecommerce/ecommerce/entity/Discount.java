package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discount")
public class Discount {
    @Id
    @Column(name = "discount_id")
    @SequenceGenerator(name="discount_gen_seq" ,sequenceName = "discount_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "discount_gen_seq")
    private int discountId;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "discount_percentage")
    private float discountPercentage;

    @Column(name="discount_status")
    private String discountStatus;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
