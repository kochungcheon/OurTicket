package ko.ourticket.ticket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    private Integer seatCount;
    private Integer fixedPrice;
    @Enumerated(value = EnumType.STRING)
    private Grade grade;

    private Long performanceId;
    private Long memberTicketId;
    public void setPerformance(Long performanceId){
        this.performanceId = performanceId;
    }


    private Ticket(final int seatCount, final int fixedPrice,
                   final Grade grade) {
        this.seatCount = seatCount;
        this.fixedPrice = fixedPrice;
        this.grade = grade;
    }
    public static Ticket of(final Integer seatCount, final Integer fixedPrice,
                            final Grade grade){
        return new Ticket(seatCount, fixedPrice, grade);
    }
    public void calculateSeat(final Integer requestCount){
        if (this.seatCount < requestCount){
            throw new RuntimeException("좌석이 부족합니다.");
        }
        this.seatCount -= requestCount;
    }
}
