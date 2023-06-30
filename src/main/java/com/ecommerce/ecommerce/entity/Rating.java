package com.ecommerce.ecommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="rating",uniqueConstraints ={ @UniqueConstraint(columnNames = {"item_id","user_id"})} )
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Rating {

    @Id
    @SequenceGenerator(name = "rating_seq_generator",sequenceName ="rating_id_seq",allocationSize  =1)
    @GeneratedValue(generator = "rating_seq_generator",strategy=GenerationType.SEQUENCE)
    private Integer ratingId;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item item;


    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(name="rating", length=2000, nullable=false)
    private double rating;

}
