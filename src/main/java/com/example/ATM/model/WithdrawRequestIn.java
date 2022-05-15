package com.example.ATM.model;


import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Min;

import static com.example.ATM.model.WithdrawRequest.WithdrawRequestBuilder.aWithdrawRequest;

public class WithdrawRequestIn {


    @Indexed(unique = false)
    @Length(max = 16)
    private String cardNumber;

    @Length(max = 10)
    private String codeNumber;

    @Min(1)
    private Double amount;


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public  WithdrawRequest toWithdrawRequest() {
        return aWithdrawRequest().amount(amount).cardNumber(cardNumber).codeNumber(codeNumber).build();
    }
}
