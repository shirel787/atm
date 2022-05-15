package com.example.ATM.controller;

import com.example.ATM.model.WithdrawRequest;
import com.example.ATM.model.WithdrawRequestIn;
import com.example.ATM.repository.WithdrawRequestRepository;
import com.example.ATM.service.WithdrawRequestService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class WithdrawReqController {

    @Autowired
    WithdrawRequestService withdrawReqService;
    @Autowired
    WithdrawRequestRepository withdrawReqRepository;

    @RequestMapping(value = "/withdrawreq", method = RequestMethod.POST)
    public ResponseEntity<?> withdrawReq(@RequestBody WithdrawRequestIn withdrawreqIn) throws IOException
    {
        WithdrawRequest withdrawRequest = withdrawreqIn.toWithdrawRequest();
        withdrawRequest.setRequestDate(DateUtils.truncate(withdrawRequest.getRequestDate(), Calendar.DATE));
        //goes to microservices
        boolean valid = withdrawReqService.validateWithDrawUsingMS(withdrawreqIn.getCardNumber(),withdrawreqIn.getAmount());
        if (!valid ) return new ResponseEntity<>("the request is not approved", HttpStatus.FORBIDDEN);
        withdrawReqService.saveRequest(withdrawRequest);
        return new ResponseEntity<>(withdrawRequest, HttpStatus.OK);
    }

//    private boolean checkWithDrawValid(WithdrawRequestIn withdrawreqIn) {
//        var withdras=withdrawReqRepository.findByCardNumberAndRequestDate(withdrawreqIn.getCardNumber(), DateUtils.truncate(new Date(),Calendar.DATE));
//        var sum =withdras.stream().mapToDouble(r-> r.getAmount()).sum();
//        if (sum + withdrawreqIn.getAmount() >= 2000 || withdras.size()>=5) {
//            return false;
//        }
//        return true;
//    }
@RequestMapping(value = "/withdrawreqDelete", method = RequestMethod.DELETE)
 public ResponseEntity<?> withdrawReqDelete(@RequestParam String requestId) throws IOException
{
    var withdrawRequest = withdrawReqRepository.findById(requestId);
    if(withdrawRequest.isEmpty())
        return new ResponseEntity<>("the request is not found", HttpStatus.FORBIDDEN);
    withdrawReqRepository.delete(withdrawRequest.get());
    return new ResponseEntity<>("the request is deleted", HttpStatus.OK);
}



}
