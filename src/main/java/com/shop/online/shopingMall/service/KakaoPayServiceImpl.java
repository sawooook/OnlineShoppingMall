package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.BillingInfo;
import com.shop.online.shopingMall.domain.Payment;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.CardName;
import com.shop.online.shopingMall.dto.util.KakaoPayApproveResponseDto;
import com.shop.online.shopingMall.dto.util.KakaoPayReadyResponseDto;
import com.shop.online.shopingMall.exception.NotFoundBillingInfoException;
import com.shop.online.shopingMall.exception.NotFoundUserException;
import com.shop.online.shopingMall.repository.BillingInfoRepository;
import com.shop.online.shopingMall.repository.PaymentRepository;
import com.shop.online.shopingMall.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import util.KakakoPayUtil;

@Service
@AllArgsConstructor
public class KakaoPayServiceImpl implements BillingInfoService {

    private final UserRepository userRepository;
    private final BillingInfoRepository billingInfoRepository;
    private final PaymentRepository paymentRepository;
    private final SecurityService securityService;

    /**
     * - 카카오 페이 Ready
     * 정기 결제 시작전에 카카오페이 준비를 하는 단계
     * */

    @Override
    public void ready() {
        Long userId = securityService.findUserIdbyToken();

        ResponseEntity<KakaoPayReadyResponseDto> responseKakao = KakakoPayUtil.readyToKakaoPay();

        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        BillingInfo makeKakaoBillingInfo = BillingInfo.builder().cardName(CardName.kakao)
                .uniqueNumber(responseKakao.getBody().getTid())
                .user(user).build();

        billingInfoRepository.save(makeKakaoBillingInfo);
    }

    /**
     * - 카카오 페이 Approve
     * 정기결제를 등록한는 단계
     * */
    @Override
    public void approve(String pgToken) {
        Long userId = securityService.findUserIdbyToken();

        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);

        BillingInfo billingInfo = user.activeBillingInfo().orElseThrow(NotFoundBillingInfoException::new);

        KakaoPayApproveResponseDto responseKakaoApprove = KakakoPayUtil.approveToKakaoPay(user, pgToken);

        billingInfo.updatePaymentKey(responseKakaoApprove.getSid());
        billingInfoRepository.save(billingInfo);
    }
}
