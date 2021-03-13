package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.BillingInfo;
import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.CardName;
import com.shop.online.shopingMall.dto.util.KakaoPayApproveResponseDto;
import com.shop.online.shopingMall.dto.util.KakaoPayChargeResponseDto;
import com.shop.online.shopingMall.dto.util.KakaoPayReadyResponseDto;
import com.shop.online.shopingMall.exception.NotFoundBillingInfoException;
import com.shop.online.shopingMall.exception.NotFoundUserException;
import com.shop.online.shopingMall.repository.BillingInfoRepository;
import com.shop.online.shopingMall.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import util.KakakoPayUtil;

@Service
@AllArgsConstructor
public class KakaoPayServiceImpl implements BillingInfoService {

    private final UserRepository userRepository;
    private final BillingInfoRepository billingInfoRepository;
    private final SecurityService securityService;
    private final PaymentService paymentService;

    /**
     * - 카카오 페이 Ready
     * 정기결제를 준비 하는 단계
     * */

    @Override
    public void ready() {
        Long userId = securityService.findUserIdbyToken();
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        if (user.hasActiveBillingInfo(CardName.kakao).isPresent()) {
            BillingInfo billingInfo = user.hasActiveBillingInfo(CardName.kakao).orElseThrow(NotFoundBillingInfoException::new);
            billingInfo.delete();
        }

        ResponseEntity<KakaoPayReadyResponseDto> responseKakao = KakakoPayUtil.readyToKakaoPay(userId);

        BillingInfo makeKakaoBillingInfo = BillingInfo.builder().cardName(CardName.kakao)
                .uniqueNumber(responseKakao.getBody().getTid())
                .user(user).build();
        billingInfoRepository.save(makeKakaoBillingInfo);
    }

    /**
     * - 카카오 페이 Approve
     * 정기결제 등록된 후 테스트 결제를 시도하는 단계
     * */
    @Override
    public void approve(String pgToken, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);

        KakaoPayApproveResponseDto responseKakao = KakakoPayUtil.approveToKakaoPay(user, pgToken);
        BillingInfo billingInfo = user.activeBillingInfo().orElseThrow(NotFoundBillingInfoException::new);
        billingInfo.updatePaymentKey(responseKakao.getSid());
        billingInfoRepository.save(billingInfo);
    }

    @Override
    public void charge(Order order) {
        paymentService.charge(KakakoPayUtil.charge(order));
    }
}
