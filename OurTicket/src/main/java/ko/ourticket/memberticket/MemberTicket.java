package ko.ourticket.memberticket;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class MemberTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long ticketId;
    private Integer realPrice;

    protected MemberTicket(){}

    private MemberTicket(final Long memberId, final Long ticketId, final Integer realPrice) {
        this.memberId = memberId;
        this.ticketId = ticketId;
        this.realPrice = realPrice;
    }
    public static MemberTicket of(final Long memberId, final Long ticketId, final Integer realPrice) {
        return new MemberTicket(memberId, ticketId, realPrice);
    }
}
