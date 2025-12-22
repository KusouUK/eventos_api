package com.vitoruk.api.controller;

import com.vitoruk.api.domain.coupon.Coupon;
import com.vitoruk.api.domain.coupon.CouponRequestDTO;
import com.vitoruk.api.domain.coupon.CouponResponseDTO;
import com.vitoruk.api.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PostMapping("/event/{eventId}")
    public ResponseEntity<CouponResponseDTO> addCouponsToEvent(@PathVariable UUID eventId, @RequestBody CouponRequestDTO data) {
        CouponResponseDTO coupons = couponService.addCouponToEvent(eventId, data);
        return ResponseEntity.ok(coupons);
    }
}
