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
@Getter
@Setter
public class Item {
    @Id
    @SequenceGenerator(name = "item_seq_generator",sequenceName ="item_id_seq",allocationSize  =1)
    @GeneratedValue(generator = "item_seq_generator",strategy=GenerationType.SEQUENCE)
    private Integer itemId;

    @Column(name="item_name", length=50, nullable=false)
    private String itemName;

    @Column(name="item_price", length=50, nullable=false)
    private Integer itemPrice;

    @Column(name="item_quantity", length=50, nullable=false)
    private Integer itemQuantity;

    @Column(name="item_description", length=2000, nullable=false)
    private String itemDescription;

    @Lob
    @Column(name="item_image")
    private String itemImage;

    @ManyToOne
    @JoinColumn(name="cat_id", nullable=false)
    private Category category;

}
