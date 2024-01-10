package com.desafio.picpay.controller;


import com.desafio.picpay.dtos.TransactionDto;
import com.desafio.picpay.model.Transactions;
import com.desafio.picpay.model.Users;
import com.desafio.picpay.repositories.UserRepositorie;
import com.desafio.picpay.repositories.transactionRespositories;
import com.desafio.picpay.service.TransactionService;
import com.desafio.picpay.service.UserService;
import com.desafio.picpay.transactionException.TransactioOperationException;
import com.desafio.picpay.transactionException.ValidationStatusUserException;
import com.desafio.picpay.transactionException.ConsultaServicoAutorizadorException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionsController {

    /*
    {
    "value": 100.0,
    "payer": 4, pagador
    "payee": 15 beneficiario
}
     */

    @Autowired
    transactionRespositories respositories;


    UserService userService;

    @Autowired
    UserRepositorie userRepositorie;


    TransactionService transactionService;


    @Autowired
    public TransactionsController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<String> saveTransaction(@RequestBody @Valid TransactionDto transactionDto) throws Exception {
        final Transactions transactions = new Transactions();

        try {
            if (transactionDto != null) {

                // verifica se os usuarios existem na base
                final Users userBeneficiario = userService.getUserById(transactionDto.payee());
                final Users userPagador = userService.getUserById(transactionDto.payer());

                // valida status dos usuarios
                userService.validationStatusUser(userPagador, transactionDto.value());

                // Consulta serviço autorizador

                transactionService.consultaServicoAutorizador();

                // Executa a transacao de debito e credito
                final Transactions operation = transactionService.transactioOperation(userPagador, userBeneficiario, transactionDto.value());

                // Persiste a transação
                ResponseEntity.status(HttpStatus.CREATED).body(respositories.save(operation));

                transactionService.recebeNotificacaoEnvio();

            }
        } catch (ValidationStatusUserException validationStatusUserException) {
            return ResponseEntity.status(400).body(validationStatusUserException.getMessage());
        } catch (ConsultaServicoAutorizadorException consultaServicoAutorizadorException) {
            return ResponseEntity.status(400).body(consultaServicoAutorizadorException.getMessage());
        } catch (TransactioOperationException transactioOperationException) {
            return ResponseEntity.status(400).body(transactioOperationException.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Transação realizada com sucesso");
    }
}