package com.example.clientTest.controller;

import com.example.clientTest.dto.User;
import com.example.clientTest.service.RestTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/client")
public class ApiController {

  private RestTemplateService restTemplateService;

  public ApiController(RestTemplateService restTemplateService) {
    this.restTemplateService = restTemplateService;
  }

  @GetMapping("")
  public User getHello(){
    return restTemplateService.post();
  }

}
