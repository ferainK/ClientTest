package com.example.restaurant.wishList.dto;

import com.example.restaurant.db.DbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListDto {
  private int index;
  private String title;                 // 가게명
  private String category;              // 카테고리
  private String lotAddress;            // 지번 주소
  private String roadAddress;           // 도로명 주소
  private String homePage;              // 홈페이지 주소
  private String image;                 // 관련 이미지
  private boolean isVisit;              // 방문 여부
  private int visitCount;               // 방문 횟수
  private LocalDateTime lastVisitDate;  // 최근 방문일
}
