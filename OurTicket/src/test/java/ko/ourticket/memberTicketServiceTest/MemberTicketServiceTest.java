package ko.ourticket.memberTicketServiceTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ko.ourticket.member.Member;
import ko.ourticket.memberticket.MemberTicket;
import ko.ourticket.memberticket.MemberTicketRepository;
import ko.ourticket.memberticket.MemberTicketService;
import ko.ourticket.ticket.Grade;
import ko.ourticket.ticket.Seat;
import ko.ourticket.ticket.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MemberTicketServiceTest {

    @InjectMocks
    private MemberTicketService memberTicketService;

    @Mock
    private MemberTicketRepository memberTicketRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("멤버에게 티켓 등록 성공")
    void registerTicketForMember_success() {
        Member mockMember = Member.of("ko", "1234");
        mockMember.addAccount(1L);
        Ticket mockTicket = Ticket.of(Seat.of(100, Grade.S), 50_000);
        Integer requestSeatCount = 1;

        memberTicketService.registerTicketForMember(mockMember, mockTicket, requestSeatCount);

        verify(memberTicketRepository, times(1)).save(any(MemberTicket.class));
    }
}