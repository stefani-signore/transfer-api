package com.itau.transferAPI.business;

import com.itau.transferAPI.dto.request.TransferRequest;
import com.itau.transferAPI.dto.response.TransferResponse;

import java.util.List;

public interface TransferService {

    TransferResponse transfer(TransferRequest request);

    List<TransferResponse> findByAccount(String accountNumber);

}