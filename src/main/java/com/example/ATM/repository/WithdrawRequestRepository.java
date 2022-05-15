package com.example.ATM.repository;

import com.example.ATM.model.WithdrawRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface WithdrawRequestRepository extends MongoRepository<WithdrawRequest, String> {
    List<WithdrawRequest> findByCardNumberAndRequestDate(String cardnumber, Date date) ;


}

