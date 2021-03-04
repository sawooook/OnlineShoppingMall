package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.BillingInfo;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.CardName;
import com.shop.online.shopingMall.dto.KakaoPayReadyResponseDto;
import com.shop.online.shopingMall.repository.BillingInfoRepository;
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

    public void kakaoPayReady(Long id) throws ChangeSetPersister.NotFoundException {
        ResponseEntity<KakaoPayReadyResponseDto> responseKakao = KakakoPayUtil.readyToKakaoPay();
        User user = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        BillingInfo makeKakaoBillingInfo = BillingInfo.builder().cardName(CardName.kakao)
                .uniqueNumber(responseKakao.getBody().getTid())
                .user(user).build();

        billingInfoRepository.save(makeKakaoBillingInfo);
    }
}
