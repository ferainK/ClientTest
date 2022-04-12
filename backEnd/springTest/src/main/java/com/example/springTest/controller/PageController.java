package com.example.springTest.controller;

import com.example.springTest.dto.CarRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //HTML이나 JSON으로 응답
public class PageController {

  // 1. HTML 응답
  @RequestMapping("/html")
  public String main() {
    return "main.html";
  }

  // 2-1. JSON 응답
  @ResponseBody
  @GetMapping("/car")
  public CarRequest carRequest() {
    var car = new CarRequest();
    car.setName("G70");
    car.setCarNumber("54러2904");
    return car;
  }

  // 2-2. JSON 응답
  // ResponseEntity

}
