package com.ecommerce.ecommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sales {
    @Id
    @SequenceGenerator(name = "sales_seq_generator", sequenceName = "sales_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_seq_generator")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User seller;

    @Column(name = "sale_date")
    private LocalDate date;

    @Column(name = "sale_quantity")
    private int quantity;

    @Column(name = "sale_total")
    private double total;



}
