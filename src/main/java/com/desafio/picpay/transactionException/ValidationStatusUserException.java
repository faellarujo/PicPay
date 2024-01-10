package com.desafio.picpay.transactionException;

public class ValidationStatusUserException extends RuntimeException{
    public ValidationStatusUserException(String mensagem) {
        super(mensagem);
    }

}
