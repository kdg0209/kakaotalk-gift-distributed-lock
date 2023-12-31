package com.gift.kakao.domain.receivedgiftbox.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ReceivedGiftBoxCreateRequest {

    @NotBlank
    private String memberId;

    @NotBlank
    private String giftSerialCode; // redis에서 저장중인 serialCode 형식에서 ex) 2307220010FA9HHIEX:2307220010J0NPV6IU

    public ReceivedGiftBoxCreateRequest(String memberId, String giftSerialCode) {
        this.memberId = memberId;
        this.giftSerialCode = giftSerialCode;
    }
}
