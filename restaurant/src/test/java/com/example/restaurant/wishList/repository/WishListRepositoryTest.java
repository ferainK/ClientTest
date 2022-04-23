package com.example.restaurant.wishList.repository;

import com.example.restaurant.wishList.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListRepositoryTest {
  @Autowired
  private WishListRepository wishListRepository;
  private WishListEntity create(){
    var wishList = new WishListEntity();
    wishList.setTitle("title");
    wishList.setCategory("category");
    wishList.setLotAddress("lotAddress");
    wishList.setRoadAddress("roadAddress");
    wishList.setHomePage("");
    wishList.setImage("");
    wishList.setVisit(false);
    wishList.setVisitCount(0);
    wishList.setLastVisitDate(null);
    return wishList;
  }

  @Test
  public void saveTest(){
    var wishListEntity = create();
    var expected = wishListRepository.save(wishListEntity);

    Assertions.assertNotNull(expected);
    Assertions.assertEquals(1, expected.getIndex());
  }

  @Test
  public void findByIdTest(){
    var wishListEntity = create();
    wishListRepository.save(wishListEntity);

    var expected = wishListRepository.findById(1);

    Assertions.assertEquals(true, expected.isPresent());
    Assertions.assertEquals(1, expected.get().getIndex());
  }

  @Test
  public void deleteTest(){
    var wishListEntity = create();
    wishListRepository.save(wishListEntity);
    wishListRepository.deleteById(1);
    int count =wishListRepository.findAll().size();
    Assertions.assertEquals(0, count);
  }

  @Test
  public void listAllTest(){
    var wishListEntity1 = create();
    wishListRepository.save(wishListEntity1);

    var wishListEntity2 = create();
    wishListRepository.save(wishListEntity2);

    int count = wishListRepository.findAll().size();
    Assertions.assertEquals(2, count);
  }
}
