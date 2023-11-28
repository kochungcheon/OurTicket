package ko.ourticket.account;

public interface AccountReader {
	Long findAccountIdBy(String nickName);

	Account findAccountBy(Long accountId);
}
