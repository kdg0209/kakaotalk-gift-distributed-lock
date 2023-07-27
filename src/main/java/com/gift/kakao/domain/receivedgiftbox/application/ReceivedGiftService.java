package com.gift.kakao.domain.receivedgiftbox.application;

import com.gift.kakao.domain.receivedgiftbox.dao.ReceivedGiftBoxRepository;
import com.gift.kakao.domain.receivedgiftbox.domain.ReceivedGiftBox;
import com.gift.kakao.domain.sendgiftbox.dao.SendGiftBoxDao;
import com.gift.kakao.domain.sendgiftbox.domain.SendGiftBox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReceivedGiftService {

    private static final int WAIT_TIME = 1;
    private static final int LEASE_TIME = 2;
    private static final String GIFT_LOCK = "gift_lock";

    private final RedissonClient redissonClient;
    private final SendGiftBoxDao sendGiftBoxDao;
    private final ReceivedGiftBoxRepository receivedGiftBoxRepository;

    public void create(String giftSerialCode, String memberId) {
        SendGiftBox sendGiftBox = sendGiftBoxDao.findByGiftSerialCode(giftSerialCode);
        if (sendGiftBox.hasAvailableQuantity()) {
            sendGiftBox.decreaseAvailableQuantity();
            ReceivedGiftBox receivedGiftBox = new ReceivedGiftBox(memberId, sendGiftBox);
            receivedGiftBoxRepository.save(receivedGiftBox);
        }
    }

//    public void create(String giftSerialCode, String memberId) {
//        RLock lock = redissonClient.getLock(GIFT_LOCK);
//        try {
//
//            boolean hasLock = lock.tryLock(WAIT_TIME, LEASE_TIME, TimeUnit.SECONDS);
//            if (!hasLock) {
//                log.info("lock 획득 실패");
//                throw new IllegalStateException("Lock을 획득하지 못하였습니다.");
//            }
//            log.info("lock 획득!!");
//
//            SendGiftBox sendGiftBox = sendGiftBoxDao.findByGiftSerialCode(giftSerialCode);
//            if (sendGiftBox.hasAvailableQuantity()) {
//                sendGiftBox.decreaseAvailableQuantity();
//                ReceivedGiftBox receivedGiftBox = new ReceivedGiftBox(memberId, sendGiftBox);
//                receivedGiftBoxRepository.save(receivedGiftBox);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//            log.info("lock 반납");
//        }
//    }
}
