package com.vitoruk.api.service;

import com.vitoruk.api.domain.coupon.Coupon;
import com.vitoruk.api.domain.coupon.CouponRequestDTO;
import com.vitoruk.api.domain.coupon.CouponResponseDTO;
import com.vitoruk.api.domain.event.Event;
import com.vitoruk.api.repositories.CouponRepository;
import com.vitoruk.api.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final EventRepository eventRepository;

    public CouponResponseDTO addCouponToEvent(UUID eventId, CouponRequestDTO data) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(data.code());
        coupon.setDiscount(data.discount());
        coupon.setValid(new Date(data.valid()));
        coupon.setEvent(event);

        couponRepository.save(coupon);

        return new CouponResponseDTO(
            coupon.getCode(),
            coupon.getDiscount(),
            coupon.getValid(),
            new CouponResponseDTO.EventDTO(
                    coupon.getEvent().getId(),
                    coupon.getEvent().getTitle(),
                    coupon.getEvent().getDescription(),
                    coupon.getEvent().getDate(),
                    coupon.getEvent().getAddress() != null ? coupon.getEvent().getAddress().getCity() : "",
                    coupon.getEvent().getAddress() != null ? coupon.getEvent().getAddress().getUf() : "",
                    coupon.getEvent().getRemote(),
                    coupon.getEvent().getEventUrl(),
                    coupon.getEvent().getImgUrl()
            )
        );
    }

    public List<Coupon> consultCoupons(UUID eventId, Date currentDate) {
        return couponRepository.findByEventIdAndValidAfter(eventId, currentDate);
    }
}
