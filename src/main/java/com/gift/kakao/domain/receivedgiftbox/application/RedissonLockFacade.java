package com.gift.kakao.domain.receivedgiftbox.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedissonLockFacade {

    private static final int WAIT_TIME = 1;
    private static final int LEASE_TIME = 2;
    private static final String GIFT_LOCK = "gift_lock";

    private final RedissonClient redissonClient;
    private final ReceivedGiftService receivedGiftService;

    public void create(String giftSerialCode, String memberId) {
        RLock lock = redissonClient.getLock(GIFT_LOCK);
        try {

            boolean hasLock = lock.tryLock(WAIT_TIME, LEASE_TIME, TimeUnit.SECONDS);
            if (!hasLock) {
                log.info("lock 획득 실패");
                throw new IllegalStateException("Lock을 획득하지 못하였습니다.");
            }
            log.info("lock 획득!!");
            receivedGiftService.create(giftSerialCode, memberId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            log.info("lock 반납");
        }
    }
}
