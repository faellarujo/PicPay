package com.desafio.picpay.service;


import com.desafio.picpay.controller.ConsultaServicoAutorizadorController;
import com.desafio.picpay.controller.ConsultaServicoEmailController;
import com.desafio.picpay.model.Transactions;
import com.desafio.picpay.model.Users;
import com.desafio.picpay.transactionException.ConsultaServicoAutorizadorException;
import com.desafio.picpay.transactionException.RecebeNotificacaoEnvioException;
import com.desafio.picpay.transactionException.TransactioOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final ConsultaServicoEmailController consultaServicoEmailController;

    private final ConsultaServicoAutorizadorController consultaServicoAutorizadorController;

    @Autowired
    public TransactionService(ConsultaServicoEmailController consultaServicoEmailController, ConsultaServicoAutorizadorController consultaServicoAutorizadorController) {
        this.consultaServicoEmailController = consultaServicoEmailController;
        this.consultaServicoAutorizadorController = consultaServicoAutorizadorController;
        
    }

    public void consultaServicoAutorizador() throws RuntimeException {

            final ResponseEntity<String> resposta = consultaServicoAutorizadorController.consultaServiço();
            if (resposta.getStatusCode().isError() || resposta.getBody().equals("Autorizado")){
                throw new ConsultaServicoAutorizadorException("Serviço não autorizado");
            };
    }
    public void recebeNotificacaoEnvio() throws RecebeNotificacaoEnvioException {

        final ResponseEntity<String> resposta = consultaServicoEmailController.consultaServiçoEmail();
        if (resposta.getStatusCode().isError() || resposta.getBody().equals("true")){
            throw new RecebeNotificacaoEnvioException("Envio de Email não realizado");
        };
    }

    @Transactional
    public Transactions transactioOperation(Users sender, Users receiver, BigDecimal valor) throws TransactioOperationException {
        try {

            final Transactions transactions = new Transactions();
            //debito
            sender.setBalance(sender.getBalance().subtract(valor));
            //credito
            receiver.setBalance(receiver.getBalance().add(valor));
            // Efetua operação
            transacao(sender, receiver, valor, transactions);
            return transactions;
        } catch (TransactioOperationException e) {
            // Se ocorreu uma exceção, a transação será revertida (rollback)
            throw new TransactioOperationException("Error during transaction");
        }
    }

    private static void transacao(Users sender, Users receiver, BigDecimal valor, Transactions transactions) {
        transactions.setAmont(valor);
        transactions.setReceiver(receiver);
        transactions.setSender(sender);
        transactions.setLocalDateTime(LocalDateTime.now());
    }
}


