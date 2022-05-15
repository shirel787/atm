package com.example.ATM.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Min;
import java.util.Date;

@Document(collection = "withdrawRequest")

public class WithdrawRequest {

    @Id
    private String id;

    @Indexed(unique = false)
    @Length(max = 16)
    private String cardNumber;

    @Length(max = 10)
    private String codeNumber;

    @Min(1)
    private Double amount;

    @Indexed(unique = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date requestDate=new Date();



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public static final class WithdrawRequestBuilder {
        private String id;
        private String cardNumber;
        private String codeNumber;
        private Double amount;
        private Date requestDate=new Date();

        private WithdrawRequestBuilder() {
        }

        public static WithdrawRequestBuilder aWithdrawRequest() {
            return new WithdrawRequestBuilder();
        }

        public WithdrawRequestBuilder id(String id) {
            this.id = id;
            return this;
        }

        public WithdrawRequestBuilder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public WithdrawRequestBuilder codeNumber(String codeNumber) {
            this.codeNumber = codeNumber;
            return this;
        }

        public WithdrawRequestBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public WithdrawRequestBuilder requestDate(Date requestDate) {
            this.requestDate = requestDate;
            return this;
        }

        public WithdrawRequest build() {
            WithdrawRequest withdrawRequest = new WithdrawRequest();
            withdrawRequest.setId(id);
            withdrawRequest.setCardNumber(cardNumber);
            withdrawRequest.setCodeNumber(codeNumber);
            withdrawRequest.setAmount(amount);
            withdrawRequest.setRequestDate(requestDate);
            return withdrawRequest;
        }
    }
}
