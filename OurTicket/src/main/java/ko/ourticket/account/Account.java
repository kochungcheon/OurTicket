package com.ticketease.te.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private Long memberId;

    protected Account(){}
    private Account(final Long amount){
        this.amount = amount;
    }
    public static Account of(final Long amount){
        return new Account(amount);
    }
}
