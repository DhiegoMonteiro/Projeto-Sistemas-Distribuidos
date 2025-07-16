package com.sd.projeto.common.models;

import java.io.Serializable;
import java.math.BigDecimal;


public class UserModel implements  Serializable{
        private static int counter = 1;


        private String id;
        private String nome;
        private String email;
        private BigDecimal balance;

        public UserModel(String nome, String email, BigDecimal balance) {
            this.id = String.valueOf(counter++);
            this.nome = nome;
            this.email = email;
            this.balance = balance;
        }

        public String getId() { return this.id; }
        public String getNome() { return this.nome; }
        public String getEmail() { return this.email; }
        public BigDecimal getBalance(){ return this.balance; }
        public void setBalance(BigDecimal balance) {this.balance = balance;}
}
