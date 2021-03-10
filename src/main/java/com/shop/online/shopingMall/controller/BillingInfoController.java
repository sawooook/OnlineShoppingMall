package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.config.JwtTokenProvider;
import com.shop.online.shopingMall.service.BillingInfoService;
import com.shop.online.shopingMall.service.SecurityService;
import javassist.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billingInfo")
@RequiredArgsConstructor
public class BillingInfoController {

    private final BillingInfoService billingInfoService;

    @GetMapping("/kakao/ready")
    public void kakaoPayReady() {
        billingInfoService.ready();
    }

    @RequestMapping("/kakao/approve")
    public void kakaoPayApprove(@RequestParam("pg_token") String pgToken) {
        billingInfoService.approve(pgToken);
    }

    @GetMapping("/kakao/fail")
    public ResponseEntity<String> kakaoPayFail() {
        return ResponseEntity.badRequest().body("결제에 실패하였습니다");
    }

    @GetMapping("/kakao/cancel")
    public ResponseEntity<String> kakaoPayCancel() {
        return ResponseEntity.badRequest().body("결제를 취소 하였습니다");
    }
}
