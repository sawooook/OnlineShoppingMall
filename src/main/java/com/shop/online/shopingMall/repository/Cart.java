//package com.shop.online.shopingMall.repository;
//
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.shop.online.shopingMall.domain.ProductOption;
//import lombok.Data;
//import org.springframework.data.redis.core.RedisHash;
//
//import java.io.Serializable;
//import java.util.List;
///**
//* 직렬화란? 객체를 전송가능한 형태로 만들어주는것
//* 역직렬화한? 그 데이터를 다시 객체로 변환해주는것
//* */
//
//
//@Data
//public class cart {
//    private Long id;
//    private String name;
//    private Long productId;
//    private List<ProductOptionList> productOptionList;
//
//    public cart() {
//    }
//
//    @JsonCreator
//    public cart(@JsonProperty("id") Long id,
//                @JsonProperty("name") String name,
//                @JsonProperty("productId") Long productId),
//                @JsonProperty(""){
//        this.id = id;
//        this.name = name;
//        this.productId = productId;
//    }
//
//    private class ProductOptionList {
//        private int productOptionId;
//        private int size;
//        private String color;
//    }
//}
