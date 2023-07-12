package com.ecommerce.ecommerce.entity;


import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

    @Column(name="item_image" ,length = 10485760)
    private String itemImage;

    @Transient
    private String imageBase64;

    @ManyToOne
    @JoinColumn(name="cat_id", nullable=false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

}
