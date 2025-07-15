package com.sd.projeto.common.models;

import java.io.Serializable;
import java.time.LocalDate;

public class CardModel implements Serializable {
    private static int counter = 1;

    private String cardId;
    private String number;
    private LocalDate expireDate;
    private String cvv;
    private String type;
    private String idOwner;

    public CardModel(String number, LocalDate expireDate, String cvv, String type, String idOwner) {
        this.cardId = String.valueOf(counter++);
        this.number = number;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.type = type;
        this.idOwner = idOwner;
    }
    public String getCardId() { return cardId; }
    public String getNumber() { return number; }
    public LocalDate getExpireDate() { return expireDate; }
    public String getType() { return type; }
    public String getCvv() { return cvv; }
    public String getIdOwner() { return idOwner; }
}
