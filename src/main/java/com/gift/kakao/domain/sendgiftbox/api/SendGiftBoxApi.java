package com.gift.kakao.domain.sendgiftbox.api;

import com.gift.kakao.domain.sendgiftbox.application.SendGiftBoxService;
import com.gift.kakao.domain.sendgiftbox.dto.SendGiftBoxCreateRequest;
import com.gift.kakao.domain.sendgiftbox.dto.SendGiftBoxCreateResponse;
import com.gift.kakao.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.gift.kakao.global.response.ResponseStatus.CODE_201;

@RestController
@RequestMapping("/send-gift-box")
@RequiredArgsConstructor
public class SendGiftBoxApi {

    private final SendGiftBoxService sendGiftBoxService;

    @PostMapping
    public BaseResponse<SendGiftBoxCreateResponse> create(@Valid @RequestBody SendGiftBoxCreateRequest request) {
        String result = sendGiftBoxService.create(request.getMemberIdx(), request.getOpenRoomCode(), request.getGiftName(), request.getGiftQuantity());
        return new BaseResponse<>(CODE_201, new SendGiftBoxCreateResponse(result));
    }
}
