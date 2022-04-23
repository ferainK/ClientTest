package com.example.restaurant.wishList.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.wishList.dto.WishListDto;
import com.example.restaurant.wishList.entity.WishListEntity;
import com.example.restaurant.wishList.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

  private final NaverClient naverClient;
  private final WishListRepository wishListRepository;
  public WishListDto search(String query) {
    // 지역 검색
    var searchLocalReq = new SearchLocalReq();
    searchLocalReq.setQuery(query);
    var searchLocalRes = naverClient.searchLocal(searchLocalReq);

    if (searchLocalRes.getTotal() > 0) {
      var localItem = searchLocalRes.getItems().stream().findFirst().get();
      var imageQuery = localItem.getTitle().replaceAll("<[^>]*>","");

      // 이미지 검색
      var searchImageReq = new SearchImageReq();
      searchImageReq.setQuery(imageQuery);

      var searchImageRes = naverClient.searchImage(searchImageReq);

      if (searchImageRes.getTotal() > 0) {
        var imageItem = searchImageRes.getItems().stream().findFirst().get();
        var result = new WishListDto();
        result.setTitle(localItem.getTitle());
        result.setCategory(localItem.getCategory());
        result.setLotAddress(localItem.getAddress());
        result.setRoadAddress(localItem.getRoadAddress());
        result.setHomePage(localItem.getLink());
        result.setImage(imageItem.getLink());
        return result;
      }
    }
    return new WishListDto();
  }

  public WishListDto add(WishListDto wishListDto) {
    var entity = dtoToEntity(wishListDto);
    var saveEntity = wishListRepository.save(entity);
    return entityToDto(saveEntity);
  }

  private WishListEntity dtoToEntity(WishListDto wishListDto){
    var entity = new WishListEntity();
    entity.setIndex(wishListDto.getIndex());
    entity.setTitle(wishListDto.getTitle());
    entity.setCategory(wishListDto.getCategory());
    entity.setLotAddress(wishListDto.getLotAddress());
    entity.setRoadAddress(wishListDto.getRoadAddress());
    entity.setHomePage(wishListDto.getHomePage());
    entity.setImage(wishListDto.getImage());
    entity.setVisit(wishListDto.isVisit());
    entity.setVisitCount(wishListDto.getVisitCount());
    entity.setLastVisitDate(wishListDto.getLastVisitDate());
    return entity;
  }

  private WishListDto entityToDto(WishListEntity wishListEntity){
    var dto = new WishListDto();
    dto.setIndex(wishListEntity.getIndex());
    dto.setTitle(wishListEntity.getTitle());
    dto.setCategory(wishListEntity.getCategory());
    dto.setLotAddress(wishListEntity.getLotAddress());
    dto.setRoadAddress(wishListEntity.getRoadAddress());
    dto.setHomePage(wishListEntity.getHomePage());
    dto.setImage(wishListEntity.getImage());
    dto.setVisit(wishListEntity.isVisit());
    dto.setVisitCount(wishListEntity.getVisitCount());
    dto.setLastVisitDate(wishListEntity.getLastVisitDate());
    return dto;
  }

  public List<WishListDto> findAll() {
    return wishListRepository.findAll()
        .stream()
        .map(it -> entityToDto(it))
        .collect(Collectors.toList());
  }

  public void delete(Integer index) {
    wishListRepository.deleteById(index);
  }

  public void addVisit(Integer index){
    var wishItem = wishListRepository.findById(index);
    if(wishItem.isPresent()){
      var item = wishItem.get();
      item.setVisit(true);
      item.setVisitCount(item.getVisitCount() + 1);
    }
  }
}
