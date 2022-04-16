package com.example.clientTest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Req<T> {

  private Header header;
  private T body;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Header{
    private int responseCode;
  }


}