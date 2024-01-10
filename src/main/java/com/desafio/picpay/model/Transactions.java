package com.desafio.picpay.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amont;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private Users receiver;

    private LocalDateTime localDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmont() {
        return amont;
    }

    public void setAmont(BigDecimal amont) {
        this.amont = amont;
    }

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
