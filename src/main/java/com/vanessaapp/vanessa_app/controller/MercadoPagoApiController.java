package com.vanessaapp.vanessa_app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/pagamento")
public class MercadoPagoApiController {
@Value("${mercadopago.token}")
private String accessToken;

@PostMapping("/criar-preferencia")
public ResponseEntity<?> criarPreferencia(@RequestBody Map<String, Object> payload) {
    String url = "https://api.mercadopago.com/checkout/preferences";
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(accessToken);
     HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);// pay load é os dados que tu deseja enviar  e o headers sao as autorizações e o tipo de conteudo

     
     try {
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return ResponseEntity.ok(response.getBody());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"erro\": \"" + e.getMessage() + "\"}");
    }
    
}
}
    
 


