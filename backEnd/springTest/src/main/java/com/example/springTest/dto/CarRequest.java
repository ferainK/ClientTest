package com.example.springTest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarRequest {
  private String name;
  @JsonProperty("car_number")
  private String carNumber;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCarNumber() {
    return carNumber;
  }

  public void setCarNumber(String carNumber) {
    this.carNumber = carNumber;
  }

  @Override
  public String toString() {
    return "CarRequest{" +
        "name='" + name + '\'' +
        ", carNum='" + carNumber + '\'' +
        '}';
  }
}
