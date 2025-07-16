package com.sd.projeto.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionModel implements Serializable {
    private static int counter = 1;

    private String transactionId;
    private String sender;
    private BigDecimal amount;
    private String reciever;
    private String title;
    private LocalDateTime sentAt = LocalDateTime.now();;

    public TransactionModel(String sender, BigDecimal amount, String reciever, String title, LocalDateTime sentAt) {
        this.transactionId = String.valueOf(counter++);
        this.sender = sender;
        this.amount = amount;
        this.reciever = reciever;
        this.title = title;
        this.sentAt = sentAt;
    }
    public String getTransactionId() { return transactionId; }
    public String getSender() { return sender; }
    public BigDecimal getAmount() { return amount; }
    public String getReciever() { return reciever; }
    public String getTitle() { return title; }
    public LocalDateTime getSentAt() { return sentAt; }
}
