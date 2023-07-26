package com.gift.kakao.global.util;

import com.gift.kakao.global.exception.ErrorCode;
import com.gift.kakao.global.response.ResponseStatus;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public final class MessageSourceUtils {

    @Resource
    private MessageSource source;

    static MessageSource messageSource;

    @PostConstruct
    public void initialize() {
        messageSource = source;
    }

    public static String getMessage(ResponseStatus responseStatus) {
        return messageSource.getMessage(responseStatus.name(), null, LocaleContextHolder.getLocale());
    }

    public static String getMessage(ErrorCode errorCode) {
        return messageSource.getMessage(errorCode.getCode(), null, LocaleContextHolder.getLocale());
    }

    public static String getMessage(ErrorCode errorCode, String[] message) {
        return messageSource.getMessage(errorCode.getCode(), message, LocaleContextHolder.getLocale());
    }
}