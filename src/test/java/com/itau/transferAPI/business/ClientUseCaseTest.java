package com.itau.transferAPI.business;

import com.itau.transferAPI.dto.request.ClientRequest;
import com.itau.transferAPI.persistence.repository.ClientRepository;
import com.itau.transferAPI.persistence.entity.ClientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientUseCaseTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientUseCase clientUseCase;

    @Test
    void deveCriarCliente() {

        ClientRequest request = new ClientRequest(
                "Stefani",
                "123",
                new BigDecimal("1000")
        );

        when(clientRepository.existsByAccountNumber("123"))
                .thenReturn(false);

        ClientEntity saved = new ClientEntity();
        saved.setId(UUID.randomUUID());
        saved.setName("Stefani");
        saved.setAccountNumber("123");
        saved.setBalance(new BigDecimal("1000"));

        when(clientRepository.save(any()))
                .thenReturn(saved);

        var response = clientUseCase.create(request);

        assertEquals("Stefani", response.name());
        assertEquals("123", response.accountNumber());

        verify(clientRepository).save(any());
    }
}
