package ko.ourticket.account;

import java.lang.reflect.Member;
import ko.ourticket.ticket.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void deductAmount(Account account, Ticket ticket, Integer requestSeatCount){
        Integer totalPaymentAmount = ticket.getFixedPrice() * requestSeatCount;
        account.deductAmount(totalPaymentAmount);
        accountRepository.save(account);
    }

    public void refundTicket(final Integer refundAmount, final Account account) {
        account.refundAmount(refundAmount);
        accountRepository.save(account);
    }
}
