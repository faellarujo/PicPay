package com.desafio.picpay.transactionException;

public class TransactioOperationException extends RuntimeException{
    public TransactioOperationException(String mensagem) {
        super(mensagem);
    }
}
