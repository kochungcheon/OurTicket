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

    private Account(final Long amount){
        this.amount = amount;
    }
    public static Account of(final Long amount){
        return new Account(amount);
    }
    public void calculate(Integer price){
        if (this.amount < price){
            throw new RuntimeException("잔액이 부족합니다.");
        }
        Long amount = this.amount - price;
        this.amount = amount;
    }
}
