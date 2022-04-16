package com.example.clientTest.service;

import com.example.clientTest.dto.Req;
import com.example.clientTest.dto.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

  public User exchange(){
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

    RequestEntity<User> requestEntity = RequestEntity
        .post(uri)
        .contentType(MediaType.APPLICATION_JSON)
        .header("x-authorization", "abcd")
        .header("custom-header", "ffff")
        .body(user);
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<User> response = restTemplate.exchange(requestEntity, User.class);

    return response.getBody();
  }

  public Req<User> genericExchange(){
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

    Req<User> req = new Req<User>();   // <> 동일하면 생략 가능
    req.setHeader(
        new Req.Header()
    );
    req.setBody(
      user
    );

    RequestEntity<Req<User>> requestEntity = RequestEntity
        .post(uri)
        .contentType(MediaType.APPLICATION_JSON)
        .header("x-authorization", "abcd")
        .header("custom-header", "ffff")
        .body(req);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Req<User>> response
        = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<User>>(){}); // <> 동일한 경우 생략 간능

    return response.getBody();
  }
}
