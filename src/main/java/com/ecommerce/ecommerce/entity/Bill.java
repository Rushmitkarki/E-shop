package com.ecommerce.ecommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @SequenceGenerator(name = "bill_seq_generator",sequenceName ="bill_id_seq",allocationSize  =1)
    @GeneratedValue(generator = "bill_seq_generator",strategy=GenerationType.SEQUENCE)
    private Integer billId;

    @ManyToOne
    @JoinColumn(name="email", nullable=false)
    private User user;

    @Column(name="bill_status", length=50)
    private String billStatus;

    @Column(name="bill_sub_amount",  nullable=false)
    private double billSubAmount;

    @Column(name="bill_address", length=50)
    private String billAddress;

    @Column(name="discount",  nullable=false)
    private double discount;

    @Column(name="tax",  nullable=false)
    private double tax;

    @Column(name="shipping", nullable=false)
    private  double shipping;

    @Column(name="bill_amount", nullable=false)
    private double billAmount;

    @Column(name="bill_date", length=50, nullable=false)
    private String billDate;
}
