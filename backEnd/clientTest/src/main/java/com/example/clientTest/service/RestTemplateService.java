package com.example.clientTest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {
  //response
  public String hello(){
    URI uri = UriComponentsBuilder
        .fromUriString("http://localhost:9090")
        .path("/server/hello")
        .encode()
        .build()
        .toUri();
    System.out.println(uri.toString());
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(uri, String.class);
    return result;
  }
}