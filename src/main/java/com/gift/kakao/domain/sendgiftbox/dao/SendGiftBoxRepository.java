package com.gift.kakao.domain.sendgiftbox.dao;

import com.gift.kakao.domain.sendgiftbox.domain.SendGiftBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendGiftBoxRepository extends JpaRepository<SendGiftBox, Long> {
}
