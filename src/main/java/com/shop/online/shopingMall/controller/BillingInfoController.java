package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.service.BillingInfoService;
import javassist.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billingInfo")
@RequiredArgsConstructor
public class BillingInfoController {

    private final BillingInfoService billingInfoService;
    @GetMapping("/kakao/ready/{id}")
    public void kakaoPayReady(@PathVariable @NonNull Long id) throws ChangeSetPersister.NotFoundException {
        billingInfoService.kakaoPayReady(id);
    }
}
