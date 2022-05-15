package com.example.ATM.service;

import com.example.ATM.model.WithdrawRequest;
import com.example.ATM.repository.WithdrawRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WithdrawRequestService {

    @Autowired
    WithdrawRequestRepository withdrawRequestRepository;
    @Autowired
    RestTemplate restTemplate;

    @Value("${withdrawRequest.ms.url}")
    String validationMSUrl;

    public WithdrawRequest saveRequest(WithdrawRequest req)
    {
        return withdrawRequestRepository.save(req);
    }
    public boolean validateWithDrawUsingMS(String cardNumber, Double amount)
    {
        return restTemplate.getForObject(validationMSUrl + "/api/validateWithdraw?cardNumber=" +cardNumber+"&amount="+amount,Boolean.class);
    }
}
