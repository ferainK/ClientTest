package com.example.springTest.controller;

import com.example.springTest.dto.CarRequest;
import com.example.springTest.dto.PostRequest;
import com.example.springTest.dto.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController //해당 Class는 REST API 처리하는 Controller  (값이나 JSON 파일으로 응답)
@RequestMapping("/api") // RequestMapping은 URI를 지정해주는 Annotation
public class ApiController {
  // 응답
  // 1. 기본형
  @GetMapping("/hello") //http://localhost:9090/api/hello
  public String hello() {
    return "hello spring boot!";
  }

  // 2. 특정값을 주소로 요청받을 때, 특정 값{name}을 받아오는 방법
  //  1) 같은 변수로 사용할 때
  @GetMapping("/path-variable/{name}")
  public String pathVariable(@PathVariable String name) {
    System.out.println("PathVariable : " + name);
    return name;
  }

  //  2) 다른 변수로 사용할 때
  @GetMapping("/variable/{id}")
  public String variable(@PathVariable(name = "id") String pathName) {
    System.out.println("PathVariable : " + pathName);
    return pathName;
  }

  // 3. 쿼리 파라미터 (?x=1&b=2&...)
  //  1) Key를 모르는 경우
  @GetMapping("/query-param")
  public String queryParam(@RequestParam Map<String, String> queryParam){
    StringBuilder sb = new StringBuilder();
    queryParam.entrySet().forEach(entry -> {
      System.out.println(entry.getKey());
      System.out.println(entry.getValue());
      System.out.println("\n");

      sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
    });
    return sb.toString();
  }
  //  2) Key가 명확한 경우
  @GetMapping("/query-param2")
  public String queryParam2(
      @RequestParam String name,
      @RequestParam String email,
      @RequestParam int age
  ){
    System.out.println(name);
    System.out.println(email);
    System.out.println(age);

    return name + ", " + email + ", " + age;
  }

  //   3) 쿼리 파라미터가 방대한 경우 : dto라는 패키지에 UserRequest 클래스를 매개변수로 사용하는 법
  @GetMapping("/query-param3")
  public String queryParam3(UserRequest userRequest) {
    System.out.println(userRequest.getName());
    System.out.println(userRequest.getEmail());
    System.out.println(userRequest.getAge());

    return userRequest.toString();
  }

  // post
  // 1. 미리 모든것을 알고 있을 때 (하드코딩)
  @PostMapping("/post")
  public void post(@RequestBody Map<String, String> requestData){
    requestData.forEach((key, value)->{
      System.out.println("key : " + key);
      System.out.println("value : " + value);
    });
  }

  // 2. dto
  @PostMapping("/post1")
  public void post1(@RequestBody PostRequest postRequest){
    System.out.println(postRequest);
  }

  //put
  @PutMapping("/put/{userId}")
  public UserRequest put(@RequestBody UserRequest userRequest, @PathVariable Long userId){
    System.out.println(userId);
    return userRequest;
  }

  //del
  @DeleteMapping("/del/{userId}")
  public void del(@PathVariable String userId, @RequestParam String account){
    System.out.println(userId);
    System.out.println(account);
  }

  // 응답 커스터마이징
  @PutMapping("/myput")
  public ResponseEntity<CarRequest> myPut(@RequestBody CarRequest carRequest){
    return ResponseEntity.status(HttpStatus.CREATED).body(carRequest);
  }
}