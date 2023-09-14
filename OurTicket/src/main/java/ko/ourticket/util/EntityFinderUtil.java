package ko.ourticket.util;

import ko.ourticket.account.Account;
import ko.ourticket.account.AccountRepository;
import ko.ourticket.member.Member;
import ko.ourticket.member.MemberRepository;
import ko.ourticket.memberticket.MemberTicket;
import ko.ourticket.memberticket.MemberTicketRepository;
import ko.ourticket.ticket.Ticket;
import ko.ourticket.ticket.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityFinderUtil {
    private final MemberRepository memberRepository;
    private final MemberTicketRepository memberTicketRepository;
    private final TicketRepository ticketRepository;
    private final AccountRepository accountRepository;

    public Member findMemberByNickName(final String nickName) {
        return memberRepository.findByNickName(nickName)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 이름입니다 닉네임 : " + nickName));
    }

    public Ticket findTicketById(final Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다 상품Id : " + ticketId));
    }

    public Account findAccountByMember(final Member member) {
        return accountRepository.findById(member.getAccountId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 계좌입니다"));
    }

    public MemberTicket findMemberTicketById(final Long memberTicketId){
        return memberTicketRepository.findById(memberTicketId)
                .orElseThrow(() -> new RuntimeException("존재하지 않은 사용자 티켓입니다"));
    }
}
