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
        if (account.getAmount() < totalPaymentAmount){
            throw new RuntimeException("잔액이 부족합니다.");
        }
        Long remainingBalance = account.getAmount() - totalPaymentAmount;
        accountRepository.save(Account.of(remainingBalance, account.getMemberId()));
    }
}
