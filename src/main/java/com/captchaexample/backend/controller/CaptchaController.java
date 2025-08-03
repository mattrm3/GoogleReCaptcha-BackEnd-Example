package com.captchaexample.backend.controller;

import org.springframework.http.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CaptchaController {

    @CrossOrigin(origins = "http://localhost:4321")
    @PostMapping("/verify-captcha")
    public ResponseEntity<String> verifyCaptcha(@RequestParam("captcha") String token) {
        String secret = "TU_SECRET_KEY"; // <- tu clave secreta de Google reCAPTCHA
        String url = "https://www.google.com/recaptcha/api/siteverify";

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", secret);
        params.add("response", token);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, params, Map.class);

        if ((Boolean) response.getBody().get("success")) {
            return ResponseEntity.ok("Captcha válido");
        } else {
            return ResponseEntity.status(403).body("Captcha inválido");
        }
    }
}