package com.desafio.picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ConsultaServicoAutorizadorController {

    private RestTemplate restTemplate;

    URI uri = new URI("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc");


    @Autowired
    public ConsultaServicoAutorizadorController(RestTemplate restTemplate) throws URISyntaxException {
        this.restTemplate = restTemplate;
    }

    public ConsultaServicoAutorizadorController() throws URISyntaxException {
    }


    @GetMapping("/consultaServicoAutorizador")
    public ResponseEntity<String> consultaServiço(){
        ResponseEntity<String> body = restTemplate.getForEntity(uri, String.class);
        return body;
    }

}
