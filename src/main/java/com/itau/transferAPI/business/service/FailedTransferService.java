package com.itau.transferAPI.business.service;

import com.itau.transferAPI.persistence.entity.TransferEntity;
import com.itau.transferAPI.persistence.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FailedTransferService {

    private final TransferRepository transferRepository;

    public FailedTransferService(
            TransferRepository transferRepository) {

        this.transferRepository = transferRepository;
    }

    @Transactional(
            propagation = Propagation.REQUIRES_NEW)
    public void save(TransferEntity transfer) {

        transferRepository.save(transfer);
    }
}