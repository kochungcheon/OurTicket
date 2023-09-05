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
    private Long realPrice;

    protected MemberTicket(){}

    private MemberTicket(final Long realPrice) {
        this.realPrice = realPrice;
    }
    public MemberTicket of(final Long realPrice) {
        return new MemberTicket(realPrice);
    }
}
