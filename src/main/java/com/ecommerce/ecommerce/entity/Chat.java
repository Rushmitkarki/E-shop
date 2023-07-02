package com.ecommerce.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Table(name = "chat")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    private Integer chatId;

    @Column(name = "chat_text", nullable = false)
    private String chatText;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private User user2;

    @Column(name = "date", nullable = false)
    private String date;
}
