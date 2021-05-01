package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.service.BillingInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.shop.online.shopingMall.util.ApiResponse;

import static com.shop.online.shopingMall.util.ApiResponse.*;

@RestController
@RequestMapping("/billingInfo")
@RequiredArgsConstructor
public class BillingInfoController {

    private final BillingInfoService billingInfoService;

    /**
    * 카카오 페이 자동결제 등록 API
    * 1) 완료 시 approve로 이동
    * 2) 실패 시 fail, cancel로 이동
    * */
    @GetMapping("/kakao/ready")
    public void kakaoPayReady(@RequestParam Long userId) {
        billingInfoService.ready(userId);
    }

    @RequestMapping("/kakao/approve/{id}")
    public ApiResponse<String> kakaoPayApprove(@RequestParam("pg_token") String pgToken, @PathVariable("id") Long id) {
        billingInfoService.approve(pgToken, id);
        return success("카드 등록 완료");
    }

    @GetMapping("/kakao/fail")
    public ApiResponse<?> kakaoPayFail() {
        return fail("결제에 실패하셨습니다");
    }

    @GetMapping("/kakao/cancel")
    public ApiResponse<?> kakaoPayCancel() {
        return fail("결제에 취소하셨습니다");
    }
}
