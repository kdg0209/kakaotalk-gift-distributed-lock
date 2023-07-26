package com.gift.kakao.domain.openroom.dto;

import lombok.Getter;

@Getter
public class OpenRoomCreateResponse {

    private final String code;

    public OpenRoomCreateResponse(String code) {
        this.code = code;
    }
}
