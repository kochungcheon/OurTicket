package ko.ourticket.memberticket;

import jakarta.transaction.Transactional;
import ko.ourticket.account.Account;
import ko.ourticket.account.AccountService;
import ko.ourticket.member.Member;
import ko.ourticket.ticket.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberTicketService {
    private final MemberTicketRepository memberTicketRepository;
    private final AccountService accountService;

    public void registerTicketForMember(Member member, Ticket ticket, Integer requestSeatCount){
        Boolean isCanceled = false;
        MemberTicket memberTicket = MemberTicket.of(member.getId(), ticket.getId(),
                ticket.getFixedPrice(), requestSeatCount, isCanceled);
        memberTicketRepository.save(memberTicket);
    }

    public void cancelMemberTicket(final MemberTicket memberTicket, final Account account) {
        Integer refundAmount = memberTicket.getRealPrice() * memberTicket.getSeatCount();
        accountService.refundTicket(refundAmount, account);
        memberTicket.cancel();
        memberTicketRepository.save(memberTicket);
    }
}
