package ko.ourticket.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long amount;
	private Long memberId;

	private Account(final Long amount, final Long memberId) {
		this.amount = amount;
		this.memberId = memberId;
	}

	public static Account of(final Long amount, final Long memberId) {
		return new Account(amount, memberId);
	}

	public void deductAmount(Integer deductionAmount) {
		if (this.amount < deductionAmount) {
			throw new RuntimeException("잔액이 부족합니다.");
		}
		Long remainderAmount = this.amount - deductionAmount;
		this.amount = remainderAmount;
	}
}
