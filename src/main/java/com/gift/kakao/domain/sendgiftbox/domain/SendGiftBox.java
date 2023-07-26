package com.gift.kakao.domain.sendgiftbox.domain;

import com.gift.kakao.domain.member.domain.Member;
import com.gift.kakao.domain.receivedgiftbox.domain.ReceivedGiftBox;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "send_gift_box")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendGiftBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Comment(value = "오픈 채팅방의 코드")
    @Column(name = "open_room_code", nullable = false, updatable = false)
    private String openRoomCode;

    @Embedded
    private GiftSerialCode giftSerialCode;

    @Comment(value = "선물의 이름")
    @Column(name = "gift_name", nullable = false)
    private String giftName;

    @Comment(value = "선물의 총 수량")
    @Column(name = "gift_quantity", nullable = false)
    private int giftQuantity;

    @Comment(value = "사용가능한 수량")
    @Column(name = "available_quantity", nullable = false)
    private int availableQuantity;

    @Comment(value = "생성일")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "sendGiftBox", fetch = FetchType.LAZY)
    private List<ReceivedGiftBox> receivedGiftBoxes;

    @Comment(value = "선물한 유저의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx", foreignKey = @ForeignKey(name = "fk_send_gift_box_member"))
    private Member member;

    public SendGiftBox(Member member, String openRoomCode, String giftName, int giftQuantity) {
        this.member = member;
        this.openRoomCode = openRoomCode;
        this.giftSerialCode = new GiftSerialCode();
        this.giftName = giftName;
        this.giftQuantity = giftQuantity;
        this.availableQuantity = giftQuantity;
        this.createdAt = LocalDateTime.now();
    }

    public String getGiftSerialCode() {
        return this.giftSerialCode.getGiftSerialCode();
    }

    public void decreaseAvailableQuantity() {
        if (this.availableQuantity <= 0) {
            return;
        }
        this.availableQuantity -= 1;
    }

    public boolean hasAvailableQuantity() {
        return this.availableQuantity > 0;
    }
}
