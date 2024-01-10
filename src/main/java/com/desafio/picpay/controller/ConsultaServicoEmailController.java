package com.desafio.picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ConsultaServicoEmailController {

    private final RestTemplate restTemplate;


    URI uriServicoEmail = new URI("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6");

    @Autowired
    public ConsultaServicoEmailController(RestTemplate restTemplate) throws URISyntaxException {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/consultaServicoEmail")
    public ResponseEntity<String> consultaServiçoEmail(){
        ResponseEntity<String> body = restTemplate.getForEntity(uriServicoEmail, String.class);
        return body;
    }
}
