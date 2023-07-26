package com.gift.kakao.domain.receivedgiftbox.dao;

import com.gift.kakao.domain.receivedgiftbox.domain.ReceivedGiftBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivedGiftBoxRepository extends JpaRepository<ReceivedGiftBox, Long> {
}
