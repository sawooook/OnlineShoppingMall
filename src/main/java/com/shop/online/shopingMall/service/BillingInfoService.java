package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.BillingInfo;
import com.shop.online.shopingMall.domain.Payment;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.CardName;
import com.shop.online.shopingMall.dto.OrderRequestDto;
import com.shop.online.shopingMall.dto.order.OrderResponseDto;
import com.shop.online.shopingMall.dto.util.KakaoPayApproveResponseDto;
import com.shop.online.shopingMall.dto.util.KakaoPayReadyResponseDto;
import com.shop.online.shopingMall.repository.BillingInfoRepository;
import com.shop.online.shopingMall.repository.PaymentRepository;
import com.shop.online.shopingMall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import util.KakakoPayUtil;

public interface BillingInfoService {

    void ready();

    void approve(String token, Long id);

}
