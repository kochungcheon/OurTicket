package ko.ourticket.ticket;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import ko.ourticket.account.Account;
import ko.ourticket.account.AccountRepository;
import ko.ourticket.account.AccountService;
import ko.ourticket.member.Member;
import ko.ourticket.member.MemberRepository;
import ko.ourticket.memberticket.MemberTicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
public class PurchaseTicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private AccountService accountService;

    @Mock
    private MemberTicketService memberTicketService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("티켓 예매 성공")
    void purchaseTicket_success() {
        // Given
        final String nickName = "ko";
        final Long ticketId = 1L;
        final Integer requestSeatCount = 1;

        Member mockMember = mock(Member.class);
        Ticket mockTicket = mock(Ticket.class);
        Account mockAccount = mock(Account.class);
        Seat mockSeat = mock(Seat.class);

        when(memberRepository.findByNickName(nickName)).thenReturn(Optional.of(mockMember));
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(mockTicket));
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(mockAccount));
        when(mockTicket.getSeat()).thenReturn(mockSeat);

        // When
        ticketService.purchaseTicket(nickName, ticketId, requestSeatCount);

        // Then
        verify(accountService, times(1)).deductAmount(mockAccount, mockTicket, requestSeatCount);
        verify(mockSeat, times(1)).reserveSeat(requestSeatCount);
        verify(ticketRepository, times(1)).save(mockTicket);
        verify(memberTicketService, times(1)).registerTicketForMember(mockMember, mockTicket, requestSeatCount);
    }

    @Test
    @DisplayName("티켓 예매 실패: 잔액 부족")
    void purchaseTicket_notEnoughBalance_shouldThrowException() {
        final String nickName = "ko";
        final Long ticketId = 1L;
        final Integer requestSeatCount = 10;

        Member mockMember = mock(Member.class);
        Ticket mockTicket = mock(Ticket.class);
        Account mockAccount = mock(Account.class);
        Seat mockSeat = mock(Seat.class);

        when(memberRepository.findByNickName(nickName)).thenReturn(Optional.of(mockMember));
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(mockTicket));
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(mockAccount));
        when(mockTicket.getSeat()).thenReturn(mockSeat);

        doThrow(RuntimeException.class).when(accountService).deductAmount(mockAccount, mockTicket, requestSeatCount);

        assertThrows(RuntimeException.class, () -> ticketService.purchaseTicket(nickName, ticketId, requestSeatCount));
    }

    @Test
    @DisplayName("티켓 예매 실패: 좌석 부족")
    void purchaseTicket_notEnoughSeats(){
        final String nickName = "ko";
        final Long ticketId = 1L;
        final Integer requestSeatCount = 10;

        Member mockMember = Member.of(nickName, "1234");
        Seat availableSeat = Seat.of(5, Grade.S);
        Ticket mockTicket = Ticket.of(availableSeat, 50_000);


        when(memberRepository.findByNickName(nickName)).thenReturn(Optional.of(mockMember));
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(mockTicket));

        assertThrows(RuntimeException.class, () -> ticketService.purchaseTicket(nickName, ticketId, requestSeatCount));
    }
}
