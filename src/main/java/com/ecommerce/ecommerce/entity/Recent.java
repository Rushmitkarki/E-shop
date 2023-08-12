package com.ecommerce.ecommerce.entity;


import jakarta.persistence.*;
import lombok.*;


@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recent{

    @Id
    @SequenceGenerator(name = "recent_seq_generator",sequenceName ="recent_id_seq",allocationSize  =1)
    @GeneratedValue(generator = "recent_seq_generator",strategy=GenerationType.SEQUENCE)
    private Integer recentId;

    @ManyToOne
    @JoinColumn(name="email", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item item;

    @Column(name="recent_date")
    private String recentDate;
}