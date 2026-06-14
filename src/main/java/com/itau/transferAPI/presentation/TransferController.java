package com.itau.transferAPI.presentation;


import com.itau.transferAPI.business.TransferService;
import com.itau.transferAPI.dto.request.TransferRequest;
import com.itau.transferAPI.dto.response.TransferResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transfers")
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping
    public TransferResponse transfer(
            @RequestBody @Valid TransferRequest request
    ) {
        return service.transfer(request);
    }

    @GetMapping("/{accountNumber}")
    public List<TransferResponse> history(
            @PathVariable String accountNumber
    ) {
        return service.findByAccount(accountNumber);
    }
}
