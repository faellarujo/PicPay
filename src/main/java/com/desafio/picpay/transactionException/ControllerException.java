package com.desafio.picpay.transactionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(ValidationStatusUserException.class)
    public ResponseEntity<String> handleMeuServicoException(ValidationStatusUserException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(RecebeNotificacaoEnvioException.class)
    public ResponseEntity<String> recebeNotificacaoEnvioException(ValidationStatusUserException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(TransactioOperationException.class)
    public ResponseEntity<String> transactioOperationException(ValidationStatusUserException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }


}
