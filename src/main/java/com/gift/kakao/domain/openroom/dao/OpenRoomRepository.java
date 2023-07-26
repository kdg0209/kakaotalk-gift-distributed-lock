package com.gift.kakao.domain.openroom.dao;

import com.gift.kakao.domain.openroom.domain.OpenRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenRoomRepository extends JpaRepository<OpenRoom, Long> {
}
