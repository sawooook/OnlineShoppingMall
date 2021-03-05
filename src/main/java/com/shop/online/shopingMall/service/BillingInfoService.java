package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.BillingInfo;
import com.shop.online.shopingMall.domain.Payment;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.CardName;
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

@Service
@RequiredArgsConstructor
public class BillingInfoService {

    private final UserRepository userRepository;
    private final BillingInfoRepository billingInfoRepository;
    private final PaymentRepository paymentRepository;

    /*
    * #- 카카오 페이 Ready
    * 정기 결제 시작전에 카카오페이 준비를 하는 단계
    * */
    public void kakaoPayReady(Long id) throws ChangeSetPersister.NotFoundException {
        ResponseEntity<KakaoPayReadyResponseDto> responseKakao = KakakoPayUtil.readyToKakaoPay();
        User user = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        BillingInfo makeKakaoBillingInfo = BillingInfo.builder().cardName(CardName.kakao)
                .uniqueNumber(responseKakao.getBody().getTid())
                .user(user).build();

        billingInfoRepository.save(makeKakaoBillingInfo);
    }

    /*
     * #- 카카오 페이 Approve
     * 정기결제를 등록한는 단계
     * */
    public void kakaoPayApprove(Long id, String pgToken) throws ChangeSetPersister.NotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        int billingInfoSize = user.getBillingInfoList().size();

        // 유저는 활성화된 카드 한장만 갖도록 한다음 -> 이 코드 리팩토링하면 좋을듯
        BillingInfo billingInfo = user.getBillingInfoList().get(billingInfoSize - 1);

        KakaoPayApproveResponseDto responseKakaoApprove = KakakoPayUtil.approveToKakaoPay(user, pgToken);

        Payment paymentBuild = Payment.builder().aid(responseKakaoApprove.getAid())
                .approvedAt(responseKakaoApprove.getApproveAt()).createdAt(responseKakaoApprove.getCreatedAt())
                .quantity(responseKakaoApprove.getQuantity())
                .cid(responseKakaoApprove.getCid()).itemName(responseKakaoApprove.getItemName()).tid(responseKakaoApprove.getTid())
                .billingInfo(billingInfo).build();

        paymentRepository.save(paymentBuild);

        billingInfo.updatePaymentKey(responseKakaoApprove.getSid());

        billingInfoRepository.save(billingInfo);
    }
}
