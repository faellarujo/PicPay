package com.desafio.picpay.repositories;

import com.desafio.picpay.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface transactionRespositories extends JpaRepository<Transactions, Long> {
}

