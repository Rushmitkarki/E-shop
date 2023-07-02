package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

@Table
@Entity
public class Report {

    @SequenceGenerator(name = "report_seq_generator", sequenceName = "report_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "report_seq_generator")
    @Id
    private int id;

    @Column(name = "report_description")
    private String reportDescription;

    @ManyToOne
    private User user;

    @ManyToOne
    private Item item;
}
