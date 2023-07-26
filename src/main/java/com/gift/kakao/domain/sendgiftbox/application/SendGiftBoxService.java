package com.gift.kakao.domain.sendgiftbox.application;

import com.gift.kakao.domain.member.dao.MemberDao;
import com.gift.kakao.domain.member.domain.Member;
import com.gift.kakao.domain.openroom.dao.OpenRoomDao;
import com.gift.kakao.domain.openroom.domain.OpenRoom;
import com.gift.kakao.domain.sendgiftbox.dao.SendGiftBoxRepository;
import com.gift.kakao.domain.sendgiftbox.domain.SendGiftBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SendGiftBoxService {

    private final MemberDao memberDao;
    private final OpenRoomDao openRoomDao;
    private final SendGiftBoxRepository sendGiftBoxRepository;

    public String create(Long memberIdx, String openRoomCode, String giftName, int giftQuantity) {
        Member member = memberDao.findByIdx(memberIdx);
        OpenRoom openRoom = openRoomDao.findByCode(openRoomCode);

        SendGiftBox sendGiftBox = new SendGiftBox(member, openRoom.code(), giftName, giftQuantity);
        sendGiftBoxRepository.save(sendGiftBox);
        return sendGiftBox.getGiftSerialCode();
    }
}
