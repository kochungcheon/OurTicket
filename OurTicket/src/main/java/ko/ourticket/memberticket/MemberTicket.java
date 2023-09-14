package ko.ourticket.memberticket;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long ticketId;
    private Integer realPrice;
    private Integer seatCount;

    private MemberTicket(final Long memberId, final Long ticketId, final Integer realPrice, final Integer seatCount) {
        this.memberId = memberId;
        this.ticketId = ticketId;
        this.realPrice = realPrice;
        this.seatCount = seatCount;
    }
    public static MemberTicket of(final Long memberId, final Long ticketId, final Integer realPrice, final Integer seatCount) {
        return new MemberTicket(memberId, ticketId, realPrice, seatCount);
    }
}
