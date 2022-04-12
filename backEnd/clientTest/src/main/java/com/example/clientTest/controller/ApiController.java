package com.example.clientTest.controller;

import com.example.clientTest.service.RestTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ApiController {

  private RestTemplateService restTemplateService;

  public ApiController(RestTemplateService restTemplateService) {
    this.restTemplateService = restTemplateService;
  }

  @GetMapping("")
  public String getHello(){
    return restTemplateService.hello();
  }
}
