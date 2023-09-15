package ko.ourticket.ticket;

import jakarta.transaction.Transactional;
import ko.ourticket.account.Account;
import ko.ourticket.account.AccountService;
import ko.ourticket.member.Member;
import ko.ourticket.memberticket.MemberTicket;
import ko.ourticket.memberticket.MemberTicketService;
import ko.ourticket.performance.Performance;
import ko.ourticket.performance.PerformanceService;
import ko.ourticket.util.EntityFinderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService{
    private final AccountService accountService;
    private final MemberTicketService memberTicketService;
    private final TicketRepository ticketRepository;
    private final PerformanceService performanceService;
    private final EntityFinderUtil entityFinderUtil;

    public GradeCount countTicketByGradeForPerformance(Performance performance) {
        return GradeCount.from(ticketRepository.findAllById(performance.getTicketIds()));
    }

    @Transactional
    public void purchaseTicket(final String nickName, final Long ticketId, final Integer requestSeatCount) {

        Member member = entityFinderUtil.findMemberByNickName(nickName);
        Ticket ticket = entityFinderUtil.findTicketById(ticketId);
        Account account = entityFinderUtil.findAccountByMember(member);
        Seat seat  = ticket.getSeat();

        accountService.deductAmount(account, ticket, requestSeatCount);

        seat.reserveSeat(requestSeatCount);
        ticketRepository.save(ticket);

        memberTicketService.registerTicketForMember(member, ticket, requestSeatCount);
    }

    @Transactional
    public synchronized void cancelTicket(final String nickName, final Long ticketId,
                                          final Integer requestSeatCount,final Long memberTicketId){
        Member member = entityFinderUtil.findMemberByNickName(nickName);
        Ticket ticket = entityFinderUtil.findTicketById(ticketId);
        Account account = entityFinderUtil.findAccountByMember(member);

        MemberTicket memberTicket = entityFinderUtil.findMemberTicketById(memberTicketId);
        Seat seat  = ticket.getSeat();
        // 현재 날짜와 공연 날짜를 비교 하고 환불 할 수있는 지 확인 하는 로직
        performanceService.isCanceled(ticket);

        //  기존 티켓 환불 로직
        memberTicketService.cancelMemberTicket(memberTicket, account);

        seat.addSeat(requestSeatCount);
        ticketRepository.save(ticket);

        // requestSeatCount  만큼 재 구매
        purchaseTicket(nickName, ticketId, requestSeatCount);
    }}
