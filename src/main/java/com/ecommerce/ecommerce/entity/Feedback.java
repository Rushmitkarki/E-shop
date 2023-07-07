package com.ecommerce.ecommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedback")
@Entity
public class Feedback {

    @Id
    @SequenceGenerator(
            name = "feedback_seq_generator",
            sequenceName = "feedback_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "feedback_seq_generator"
    )
    private int feedbackId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private User user;

    @Column(nullable = false,name = "feedback")
    private String feedback;
}
