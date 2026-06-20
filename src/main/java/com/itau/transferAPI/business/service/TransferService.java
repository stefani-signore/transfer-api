package com.itau.transferAPI.business.service;

import com.itau.transferAPI.common.exception.BusinessException;
import com.itau.transferAPI.common.exception.NotFoundException;
import com.itau.transferAPI.domain.TransferStatus;
import com.itau.transferAPI.dto.request.TransferRequest;
import com.itau.transferAPI.dto.response.TransferResponse;
import com.itau.transferAPI.persistence.entity.ClientEntity;
import com.itau.transferAPI.persistence.entity.TransferEntity;
import com.itau.transferAPI.persistence.repository.ClientRepository;
import com.itau.transferAPI.persistence.repository.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransferService {

    private static final BigDecimal MAX_TRANSFER =
            new BigDecimal("10000.00");

    private final ClientRepository clientRepository;
    private final TransferRepository transferRepository;
    private final FailedTransferService failedTransferService;

    public TransferService(
            ClientRepository clientRepository,
            TransferRepository transferRepository,
            FailedTransferService failedTransferService) {

        this.clientRepository = clientRepository;
        this.transferRepository = transferRepository;
        this.failedTransferService = failedTransferService;
    }

    @Transactional
    public TransferResponse transfer(TransferRequest request) {

        try {

            ClientEntity source = clientRepository
                    .findByAccountNumberForUpdate(
                            request.sourceAccount())
                    .orElseThrow(() ->
                            new NotFoundException(
                                    "Conta origem não encontrada"));

            ClientEntity destination = clientRepository
                    .findByAccountNumberForUpdate(
                            request.destinationAccount())
                    .orElseThrow(() ->
                            new NotFoundException(
                                    "Conta destino não encontrada"));

            if (request.amount().compareTo(MAX_TRANSFER) > 0) {
                throw new BusinessException(
                        "Transferência acima do limite de R$ 10.000");
            }

            if (source.getBalance()
                    .compareTo(request.amount()) < 0) {

                throw new BusinessException(
                        "Saldo insuficiente");
            }

            source.setBalance(
                    source.getBalance()
                            .subtract(request.amount()));

            destination.setBalance(
                    destination.getBalance()
                            .add(request.amount()));

            clientRepository.save(source);
            clientRepository.save(destination);

            TransferEntity transfer = new TransferEntity(
                    UUID.randomUUID(),
                    request.sourceAccount(),
                    request.destinationAccount(),
                    request.amount(),
                    TransferStatus.SUCCESS,
                    LocalDateTime.now()
            );

            transferRepository.save(transfer);

            return new TransferResponse(
                    transfer.getId(),
                    transfer.getSourceAccount(),
                    transfer.getDestinationAccount(),
                    transfer.getAmount(),
                    transfer.getStatus().name(),
                    transfer.getCreatedAt()
            );

        } catch (RuntimeException ex) {

            TransferEntity failedTransfer =
                    new TransferEntity(
                            UUID.randomUUID(),
                            request.sourceAccount(),
                            request.destinationAccount(),
                            request.amount(),
                            TransferStatus.FAILED,
                            LocalDateTime.now()
                    );

            failedTransferService.save(failedTransfer);

            throw ex;
        }
    }

    public List<TransferResponse> findByAccount(
            String accountNumber) {

        return transferRepository
                .findBySourceAccountOrDestinationAccountOrderByCreatedAtDesc(
                        accountNumber,
                        accountNumber)
                .stream()
                .map(transfer -> new TransferResponse(
                        transfer.getId(),
                        transfer.getSourceAccount(),
                        transfer.getDestinationAccount(),
                        transfer.getAmount(),
                        transfer.getStatus().name(),
                        transfer.getCreatedAt()
                ))
                .toList();
    }

}
