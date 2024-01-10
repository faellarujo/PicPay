package com.desafio.picpay.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ConversorMonetario {

    public BigDecimal toBigDecimal(String valor){
        try {
            return new BigDecimal(valor).setScale(4, RoundingMode.HALF_EVEN);
        } catch (Exception ex){
            return BigDecimal.ZERO;
        }
    }
}
