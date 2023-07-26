package com.gift.kakao.domain.openroom.dto;

import lombok.Getter;

@Getter
public class OpenRoomJoinResponse {

    private final long openRoomIdx;

    public OpenRoomJoinResponse(long openRoomIdx) {
        this.openRoomIdx = openRoomIdx;
    }
}
