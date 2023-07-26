package com.gift.kakao.domain.member.api;

import com.gift.kakao.domain.member.application.MemberService;
import com.gift.kakao.domain.member.dto.MemberCreateRequest;
import com.gift.kakao.domain.member.dto.MemberCreateResponse;
import com.gift.kakao.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.gift.kakao.global.response.ResponseStatus.CODE_201;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberService memberService;

    @PostMapping
    public BaseResponse<MemberCreateResponse> create(@Valid @RequestBody MemberCreateRequest request) {
        Long result = memberService.create(request.getId());
        return new BaseResponse<>(CODE_201, new MemberCreateResponse(result));
    }
}
