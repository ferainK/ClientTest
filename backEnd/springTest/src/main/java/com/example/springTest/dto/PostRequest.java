package com.example.springTest.dto;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequest {
  private String account;
  private String password;
  private String email;
  private String address;
  @JsonProperty("phone_number")
  private String phoneNumber;
  @JsonProperty("OTP")
  private String OTP;

  public String getOTP() {
    return OTP;
  }

  public void setOTP(String OTP) {
    this.OTP = OTP;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "PostRequest{" +
        "account='" + account + '\'' +
        ", password='" + password + '\'' +
        ", email='" + email + '\'' +
        ", address='" + address + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", OTP='" + OTP + '\'' +
        '}';
  }
}
