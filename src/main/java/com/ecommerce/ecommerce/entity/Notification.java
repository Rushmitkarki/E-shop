package com.ecommerce.ecommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "notify")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @SequenceGenerator(sequenceName = "notify_id_generator" ,name="notify_seq_generator",allocationSize = 1)
    @GeneratedValue(generator = "notify_seq_generator",strategy = GenerationType.SEQUENCE)
    private int notifyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="notify_content")
    private String notifyContent;




}
