package com.gift.kakao.domain.receivedgiftbox.api;

import com.gift.kakao.domain.receivedgiftbox.application.ReceivedGiftService;
import com.gift.kakao.domain.receivedgiftbox.application.RedissonLockFacade;
import com.gift.kakao.domain.receivedgiftbox.dto.ReceivedGiftBoxCreateRequest;
import com.gift.kakao.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.gift.kakao.global.response.ResponseStatus.CODE_201;

@RestController
@RequestMapping("/received-gift-box")
@RequiredArgsConstructor
public class ReceivedGiftBoxApi {

    private final RedissonLockFacade redissonLockFacade;
    private final ReceivedGiftService receivedGiftService;

    @PostMapping
    public BaseResponse<Void> create(@Valid @RequestBody ReceivedGiftBoxCreateRequest request) {
        redissonLockFacade.create(request.getGiftSerialCode(), request.getMemberId());
        return new BaseResponse<>(CODE_201);
    }
}
