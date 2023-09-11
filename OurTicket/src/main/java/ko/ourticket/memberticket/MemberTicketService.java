package ko.ourticket.memberticket;

import ko.ourticket.member.Member;
import ko.ourticket.ticket.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberTicketService {
    private final MemberTicketRepository memberTicketRepository;

    public void registerTicketForMember(Member member, Ticket ticket, Integer requestSeatCount){
        MemberTicket memberTicket = MemberTicket.of(member.getId(), ticket.getId(),
                ticket.getFixedPrice(), requestSeatCount);
        memberTicketRepository.save(memberTicket);
    }
}