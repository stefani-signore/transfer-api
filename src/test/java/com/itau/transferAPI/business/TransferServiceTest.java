package com.itau.transferAPI.business;

import com.itau.transferAPI.business.service.TransferService;
import com.itau.transferAPI.dto.request.TransferRequest;
import com.itau.transferAPI.persistence.repository.ClientRepository;
import com.itau.transferAPI.persistence.repository.TransferRepository;
import com.itau.transferAPI.persistence.entity.ClientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private TransferService transferService;

    @Test
    void deveTransferirComSucesso() {

        ClientEntity source = new ClientEntity();
        source.setAccountNumber("111");
        source.setBalance(new BigDecimal("1000"));

        ClientEntity destination = new ClientEntity();
        destination.setAccountNumber("222");
        destination.setBalance(new BigDecimal("500"));

        when(clientRepository.findByAccountNumberForUpdate("111"))
                .thenReturn(Optional.of(source));

        when(clientRepository.findByAccountNumberForUpdate("222"))
                .thenReturn(Optional.of(destination));

        TransferRequest request =
                new TransferRequest(
                        "111",
                        "222",
                        new BigDecimal("100")
                );

        transferService.transfer(request);

        assertEquals(
                new BigDecimal("900"),
                source.getBalance()
        );

        assertEquals(
                new BigDecimal("600"),
                destination.getBalance()
        );
    }
}