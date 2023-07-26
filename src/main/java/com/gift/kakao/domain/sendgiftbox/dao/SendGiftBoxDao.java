package com.gift.kakao.domain.sendgiftbox.dao;

import com.gift.kakao.domain.sendgiftbox.domain.SendGiftBox;
import com.gift.kakao.global.exception.BusinessException;
import com.gift.kakao.global.exception.ErrorCode;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.gift.kakao.domain.sendgiftbox.domain.QSendGiftBox.sendGiftBox;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SendGiftBoxDao {

    private final JPAQueryFactory queryFactory;

    public SendGiftBox findByGiftSerialCode(String giftSerialCode) {
        SendGiftBox result = queryFactory
                .selectFrom(sendGiftBox)
                .where(
                        eqGiftSerialCode(giftSerialCode)
                )
                .fetchFirst();

        return Optional.ofNullable(result)
                .orElseThrow(() -> new BusinessException(ErrorCode.GIFT_NOT_FOUND_EXCEPTION));
    }

    private BooleanExpression eqGiftSerialCode(String giftSerialCode) {
        if (giftSerialCode == null) return null;
        return sendGiftBox.giftSerialCode.giftSerialCode.eq(giftSerialCode);
    }
}
