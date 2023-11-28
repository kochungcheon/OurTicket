package ko.ourticket.account;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	private final AccountWriter accountWriter;
	private final AccountReader accountReader;

	public void deductAmountOnPayment(String nickName, Integer totalPaymentAmount) {
		Long accountId = accountReader.findAccountIdBy(nickName);
		Account account = accountReader.findAccountBy(accountId);
		account.deductAmount(totalPaymentAmount);
		accountWriter.persist(account);
	}
}
