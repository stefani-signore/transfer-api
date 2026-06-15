package com.itau.transferAPI.presentation;

import com.itau.transferAPI.business.ClientService;
import com.itau.transferAPI.dto.request.ClientRequest;
import com.itau.transferAPI.dto.response.ClientResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ClientResponse> create(
            @RequestBody @Valid ClientRequest request) {

        ClientResponse response =
                service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll() {

        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<ClientResponse>
    findByAccountNumber(
            @PathVariable String accountNumber) {

        return ResponseEntity.ok(
                service.findByAccountNumber(accountNumber)
        );
    }
}