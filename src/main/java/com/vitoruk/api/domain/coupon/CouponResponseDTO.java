package com.vitoruk.api.domain.coupon;

import com.vitoruk.api.domain.event.EventResponseDTO;

import java.util.Date;
import java.util.UUID;

public record CouponResponseDTO(
        String code,
        Integer discount,
        Date valid,
        EventDTO event
) {
    public record EventDTO(
            UUID id,
            String title,
            String description,
            Date date,
            String city,
            String state,
            Boolean remote,
            String eventUrl,
            String imgUrl
    ) {}
}
