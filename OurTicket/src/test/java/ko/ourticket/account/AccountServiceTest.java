package ko.ourticket.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ko.ourticket.ticket.Grade;
import ko.ourticket.ticket.Seat;
import ko.ourticket.ticket.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("잔액이 충분할 때 금액 차감 성공")
    void deductAmount_success() {
        Account mockAccount = Account.of(100_000L, 1L);
        Ticket mockTicket = Ticket.of(Seat.of(100, Grade.S), 50_000);

        Integer requestSeatCount = 1;
        accountService.deductAmount(mockAccount, mockTicket, requestSeatCount);

        assertEquals(50_000L, mockAccount.getAmount().longValue());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    @DisplayName("잔액이 부족할 때 예외 발생")
    void deductAmount_insufficientBalance_shouldThrowException() {
        Account mockAccount = Account.of(40_000L, 1L);
        Ticket mockTicket = Ticket.of(Seat.of(100, Grade.S), 50_000);

        Integer requestSeatCount = 1;
        assertThrows(RuntimeException.class, () -> accountService.deductAmount(mockAccount, mockTicket, requestSeatCount));
    }
}