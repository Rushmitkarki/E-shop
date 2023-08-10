package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Favorite {

    @Id
    @SequenceGenerator(name="fav_seq_name",sequenceName="fav_id_seq",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="fav_seq_name")
    private int favId;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
