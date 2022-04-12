package com.example.springTest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)  //null 값이 잇으면 생략함

// 주의사항
// - get/set매서드를 제외한 일반 메서드에는 get이라는 표현이 들어가면 오류가 난다.
// - 물론 get/set 매서드는 반드시 있어야 한다.
// - default 생성자는 반드시 있어야 한다.
// - JSON은 스네이크 케이스, JAVA는 카멜 케이스라는점 꼭 유의하여야한다. @JasonNaming/@JsonProperty 사용하서 변환시켜줘야함

public class UserRequest {
  private String name;
  private String email;
  private int age;    //int와 Integer의 차이 : Integer은 null표현 가능
  private List<CarRequest> carList;

  public List<CarRequest> getCarList() {
    return carList;
  }

  public void setCarList(List<CarRequest> carList) {
    this.carList = carList;
  }

  //Get & Set 자동 생성
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "UserRequest{" +
        "name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", age=" + age +
        ", carList=" + carList +
        '}';
  }
}
