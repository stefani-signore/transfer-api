package com.itau.transferAPI.business;

import com.itau.transferAPI.dto.request.ClientRequest;
import com.itau.transferAPI.dto.response.ClientResponse;
import com.itau.transferAPI.persistence.repository.ClientRepository;
import com.itau.transferAPI.persistence.entity.ClientEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientUseCase implements ClientService {

    private final ClientRepository clientRepository;

    public ClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientResponse create(ClientRequest request) {

        if (clientRepository.existsByAccountNumber(
                request.accountNumber())) {

            throw new IllegalArgumentException(
                    "Número da conta já cadastrado");
        }

        ClientEntity entity = new ClientEntity();
        entity.setId(UUID.randomUUID());
        entity.setName(request.name());
        entity.setAccountNumber(request.accountNumber());
        entity.setBalance(request.balance());

        ClientEntity saved = clientRepository.save(entity);

        return new ClientResponse(
                saved.getId(),
                saved.getName(),
                saved.getAccountNumber(),
                saved.getBalance()
        );
    }

    @Override
    public List<ClientResponse> findAll() {

        return clientRepository.findAll()
                .stream()
                .map(client -> new ClientResponse(
                        client.getId(),
                        client.getName(),
                        client.getAccountNumber(),
                        client.getBalance()
                ))
                .toList();
    }

    @Override
    public ClientResponse findByAccountNumber(
            String accountNumber) {

        ClientEntity client = clientRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Cliente não encontrado"));

        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getAccountNumber(),
                client.getBalance()
        );
    }
}