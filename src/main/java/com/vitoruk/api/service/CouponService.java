package com.vitoruk.api.service;

import com.vitoruk.api.domain.coupon.Coupon;
import com.vitoruk.api.domain.coupon.CouponRequestDTO;
import com.vitoruk.api.domain.event.Event;
import com.vitoruk.api.repositories.CouponRepository;
import com.vitoruk.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private EventRepository eventRepository;

    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO data) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(data.code());
        coupon.setDiscount(data.discount());
        coupon.setValid(new Date(data.valid()));
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }
}
