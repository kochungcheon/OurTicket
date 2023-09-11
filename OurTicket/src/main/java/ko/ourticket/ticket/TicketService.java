package ko.ourticket.ticket;

import jakarta.transaction.Transactional;
import ko.ourticket.account.Account;
import ko.ourticket.account.AccountRepository;
import ko.ourticket.account.AccountService;
import ko.ourticket.member.Member;
import ko.ourticket.member.MemberRepository;
import ko.ourticket.memberticket.MemberTicketService;
import ko.ourticket.performance.Performance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService{
    private final AccountService accountService;
    private final MemberTicketService memberTicketService;
    private final TicketRepository ticketRepository;
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;

    public GradeCount countTicketByGradeForPerformance(Performance performance) {
        return GradeCount.from(ticketRepository.findAllById(performance.getTicketIds()));
    }

    @Transactional
    public void purchaseTicket(final String nickName, final Long ticketId, final Integer requestSeatCount) {

        Member member = memberRepository.findByNickName(nickName)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 이름입니다 닉네임 : " + nickName));
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다 상품Id : " + ticketId));
        Account account = accountRepository.findById(member.getAccountId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 계좌입니다"));
        Seat seat  = ticket.getSeat();

        accountService.deductAmount(account, ticket, requestSeatCount);

        seat.reserveSeat(requestSeatCount);
        ticketRepository.save(ticket);

        memberTicketService.registerTicketForMember(member, ticket, requestSeatCount);
    }
}
