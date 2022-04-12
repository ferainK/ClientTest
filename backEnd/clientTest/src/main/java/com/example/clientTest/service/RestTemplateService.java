package com.example.clientTest.service;

import com.example.clientTest.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {
  //response
  public User hello(){
    URI uri = UriComponentsBuilder
        .fromUriString("http://localhost:9090")
        .path("/server")
        .queryParam("name", "ferain")
        .queryParam("age", 30)
        .encode()
        .build()
        .toUri();
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<User> result = restTemplate.getForEntity(uri, User.class);

    System.out.println(result.getStatusCode());
    System.out.println(result.getBody());
    return result.getBody();
  }

  public User post(){
    URI uri = UriComponentsBuilder
        .fromUriString("http://localhost:9090")
        .path("/server/{userName}/{userAge}")
        .encode()
        .build()
        .expand("ferain", 9)
        .toUri();

    User user = new User();
    user.setName("ferain");
    user.setAge(30);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<User> response = restTemplate.postForEntity(uri, user, User.class);

    System.out.println(response.getStatusCode());
    System.out.println(response.getHeaders());
    System.out.println(response.getBody());
    return user;

  }
}
