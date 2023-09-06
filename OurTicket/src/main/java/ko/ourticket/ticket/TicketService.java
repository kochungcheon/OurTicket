package ko.ourticket.ticket;

import jakarta.transaction.Transactional;
import ko.ourticket.account.Account;
import ko.ourticket.account.AccountRepository;
import ko.ourticket.member.Member;
import ko.ourticket.member.MemberRepository;
import ko.ourticket.memberticket.MemberTicket;
import ko.ourticket.memberticket.MemberTicketRepository;
import ko.ourticket.performance.Performance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService{
    private final TicketRepository ticketRepository;
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final MemberTicketRepository memberTicketRepository;

    public GradeCount countTicketByGradeForPerformance(Performance performance) {
        return GradeCount.from(ticketRepository.findAllById(performance.getTicketIds()));
    }
    // 1. 동시 요청 (a,b 동시요청 , 사용자 중복 요청
    // 동시성 처리
    // 책임을 분산 시키는 것
    // 티켓 서비스 이정도의 책임을 갖는 게 맞나
    @Transactional
    public void purchaseTicket(final String nickName, final Long ticketId, final Integer requestSeatCount) {
        // 런타임 익셉션 던지는 게 애매 , (커스텀, 익셥션 위치)
        // 예외 처리 너무 불안 이걸로 뭘 할 수 있는 지 고민
        Member member = memberRepository.findByNickName(nickName)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 이름입니다 닉네임 : " + nickName));
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다 상품Id : " + ticketId));
        Account account = accountRepository.findById(member.getAccountId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 계좌입니다"));
        // account 처리 되야 한다.
        // validation
        if (account.getAmount() < ticket.getFixedPrice()){
            throw new RuntimeException("잔액이 부족합니다");
        }
        // 동시 요청 좌석 1 좌석이 구매가 되는
        // 좌석의 계수 줄여주기
        // 서비스 단 계산 저장 로직
        ticket.calculateSeat(requestSeatCount);
        ticketRepository.save(ticket);
        // 100 80 -> 20 2
        // 계좌
        // 애매
        account.calculate(ticket.getFixedPrice() * requestSeatCount);
        accountRepository.save(account);

        MemberTicket memberTicket = MemberTicket.of(member.getId(), ticketId, ticket.getFixedPrice(), requestSeatCount);
        memberTicketRepository.save(memberTicket);
    }
}
