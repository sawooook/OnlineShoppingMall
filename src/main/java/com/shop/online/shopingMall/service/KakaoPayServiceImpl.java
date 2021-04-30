package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.BillingInfo;
import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.BillingInfoStatus;
import com.shop.online.shopingMall.domain.enumType.CardName;
import com.shop.online.shopingMall.dto.util.KakaoPayApproveResponseDto;
import com.shop.online.shopingMall.dto.order.OrderResultResponseDto;
import com.shop.online.shopingMall.dto.util.KakaoPayReadyResponseDto;
import com.shop.online.shopingMall.exception.NotFoundBillingInfoException;
import com.shop.online.shopingMall.exception.NotFoundUserException;
import com.shop.online.shopingMall.repository.BillingInfoRepository;
import com.shop.online.shopingMall.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.KakakoPayUtil;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class KakaoPayServiceImpl implements BillingInfoService {

    private final UserRepository userRepository;
    private final BillingInfoRepository billingInfoRepository;

    /**
     * - 카카오 페이 Ready
     * 정기결제를 준비 하는 단계
     * 만약에 기존에 등록된 카드가 있을 경우 해당 카드는 비활성화 상태로 변경 하고
     * 새롭게 등록을 진행한다.
     * */

    @Override
    @Transactional
    public void ready(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        Optional<BillingInfo> billingInfo = user.activeBillingInfo();

        if (billingInfo.isPresent()) {
            billingInfo.get().updateInActive();
            billingInfoRepository.save(billingInfo.get());
        }

        ResponseEntity<KakaoPayReadyResponseDto> responseKakao = KakakoPayUtil.readyToKakaoPay(userId);

        BillingInfo makeKakaoBillingInfo = BillingInfo.builder().cardName(CardName.kakao)
                .uniqueNumber(responseKakao.getBody().getTid())
                .user(user).billingInfoStatus(BillingInfoStatus.ACTIVE).build();
        billingInfoRepository.save(makeKakaoBillingInfo);
    }

    /**
     * - 카카오 페이 Approve
     * 정기결제 등록된 후 테스트 결제를 시도하는 단계
     * */
    @Override
    @Transactional
    public void approve(String pgToken, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);


        BillingInfo billingInfo = billingInfoRepository.activeBillingInfo(user)
                .orElseThrow(NotFoundBillingInfoException::new);

        KakaoPayApproveResponseDto responseKakao = KakakoPayUtil.approveToKakaoPay(billingInfo, pgToken);

        billingInfo.updatePaymentKey(responseKakao.getSid());
        billingInfoRepository.save(billingInfo);
    }

    /**
    * 카카오페이 결제를 완료하고, 결제가 끝나게 될 경우
    * 카카오페이측으로 부터 받은 response값을 DTO에 담아서
    * return 한다.
    * */
    @Override
    @Transactional
    public OrderResultResponseDto charge(Order order) {
        OrderResultResponseDto responseDto = KakakoPayUtil.charge(order);
        return responseDto;
    }

    @Override
    public BillingInfo isActiveBillingInfo(User user) {
        return billingInfoRepository.activeBillingInfo(user).orElseThrow(NotFoundBillingInfoException::new);
    }
}
