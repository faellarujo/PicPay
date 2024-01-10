package com.desafio.picpay.transactionException;

public class ConsultaServicoAutorizadorException extends RuntimeException{
    public ConsultaServicoAutorizadorException(String mensagem) {
        super(mensagem);
    }
}
