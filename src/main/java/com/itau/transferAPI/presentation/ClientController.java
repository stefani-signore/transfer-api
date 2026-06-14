package com.itau.transferAPI.presentation;

import com.itau.transferAPI.business.ClientService;
import com.itau.transferAPI.dto.request.ClientRequest;
import com.itau.transferAPI.dto.response.ClientResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ClientResponse create(
            @RequestBody @Valid ClientRequest request
    ) {
        return service.create(request);
    }

    @GetMapping
    public List<ClientResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{accountNumber}")
    public ClientResponse findByAccountNumber(
            @PathVariable String accountNumber
    ) {
        return service.findByAccountNumber(accountNumber);
    }
}