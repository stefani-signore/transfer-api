package com.itau.transferAPI.business;

import com.itau.transferAPI.dto.request.ClientRequest;
import com.itau.transferAPI.dto.response.ClientResponse;

import java.util.List;

public interface ClientService {

    ClientResponse create(ClientRequest request);

    List<ClientResponse> findAll();

    ClientResponse findByAccountNumber(String accountNumber);

}
