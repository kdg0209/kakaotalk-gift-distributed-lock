package com.gift.kakao.domain.sendgiftbox.dto;


import lombok.Getter;

@Getter
public class SendGiftBoxCreateResponse {

    private final String giftSerialCode;

    public SendGiftBoxCreateResponse(String giftSerialCode) {
        this.giftSerialCode = giftSerialCode;
    }
}
