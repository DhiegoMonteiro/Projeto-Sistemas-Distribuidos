package com.sd.projeto.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionModel implements Serializable {
    private static int counter = 1;

    private String transactionId;
    private String sender;
    private BigDecimal amount;
    private String receiver;
    private String title;
    private LocalDateTime sentAt = LocalDateTime.now();;

    public TransactionModel(String sender, BigDecimal amount, String receiver, String title, LocalDateTime sentAt) {
        this.transactionId = String.valueOf(counter++);
        this.sender = sender;
        this.amount = amount;
        this.receiver = receiver;
        this.title = title;
        this.sentAt = sentAt;
    }
    public String getTransactionId() { return transactionId; }
    public String getSender() { return sender; }
    public BigDecimal getAmount() { return amount; }
    public String getReceiver() { return receiver; }
    public String getTitle() { return title; }
    public LocalDateTime getSentAt() { return sentAt; }
}
