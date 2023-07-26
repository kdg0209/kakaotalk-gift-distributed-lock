package com.gift.kakao.domain.openroom.api;

import com.gift.kakao.domain.openroom.application.OpenRoomService;
import com.gift.kakao.domain.openroom.dto.OpenRoomCreateRequest;
import com.gift.kakao.domain.openroom.dto.OpenRoomCreateResponse;
import com.gift.kakao.domain.openroom.dto.OpenRoomJoinRequest;
import com.gift.kakao.domain.openroom.dto.OpenRoomJoinResponse;
import com.gift.kakao.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.gift.kakao.global.response.ResponseStatus.CODE_201;

@RestController
@RequestMapping("/open-rooms")
@RequiredArgsConstructor
public class OpenRoomApi {

    private final OpenRoomService openRoomService;

    @PostMapping
    public BaseResponse<OpenRoomCreateResponse> create(@Valid @RequestBody OpenRoomCreateRequest request) {
        String result = openRoomService.create(request.getMemberIdx());
        return new BaseResponse<>(CODE_201, new OpenRoomCreateResponse(result));
    }

    @PostMapping("/join")
    public BaseResponse<OpenRoomJoinResponse> join(@Valid @RequestBody OpenRoomJoinRequest request) {
        Long result = openRoomService.join(request.getMemberIdx(), request.getParticipationCode());
        return new BaseResponse<>(CODE_201, new OpenRoomJoinResponse(result));
    }
}
