package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.service.BillingInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import util.ApiResponse;

import static util.ApiResponse.*;

@RestController
@RequestMapping("/billingInfo")
@RequiredArgsConstructor
public class BillingInfoController {

    private final BillingInfoService billingInfoService;

    @GetMapping("/kakao/ready")
    public void kakaoPayReady() {
        billingInfoService.ready();
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
