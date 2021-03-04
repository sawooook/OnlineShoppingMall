package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.BillingInfo;
import com.shop.online.shopingMall.domain.Payment;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.CardName;
import com.shop.online.shopingMall.dto.KakaoPayApproveResponseDto;
import com.shop.online.shopingMall.dto.KakaoPayReadyResponseDto;
import com.shop.online.shopingMall.repository.BillingInfoRepository;
import com.shop.online.shopingMall.repository.PaymentRepository;
import com.shop.online.shopingMall.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import util.KakakoPayUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillingInfoService {

    private final UserRepository userRepository;
    private final BillingInfoRepository billingInfoRepository;
    private final PaymentRepository paymentRepository;

    public void kakaoPayReady(Long id) throws ChangeSetPersister.NotFoundException {
        ResponseEntity<KakaoPayReadyResponseDto> responseKakao = KakakoPayUtil.readyToKakaoPay();
        User user = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        BillingInfo makeKakaoBillingInfo = BillingInfo.builder().cardName(CardName.kakao)
                .uniqueNumber(responseKakao.getBody().getTid())
                .user(user).build();

        billingInfoRepository.save(makeKakaoBillingInfo);
    }

    public void kakaoPayAprove(Long id) throws ChangeSetPersister.NotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        KakaoPayApproveResponseDto responseKakaoApprove = KakakoPayUtil.approveToKakaoPay(user);

        Payment paymentBuild = Payment.builder().aid(responseKakaoApprove.getAid()).amount(responseKakaoApprove.getAmount())
                .approveAt(responseKakaoApprove.getApproveAt()).createdAt(responseKakaoApprove.getCreatedAt())
                .cardInfo(responseKakaoApprove.getCardInfo()).quantity(responseKakaoApprove.getQuantity())
                .cid(responseKakaoApprove.getCid()).itemName(responseKakaoApprove.getItemName()).tid(responseKakaoApprove.getTid()).build();

        paymentRepository.save(paymentBuild);

    }
}
