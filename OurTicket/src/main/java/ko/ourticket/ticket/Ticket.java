package ko.ourticket.ticket;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
    @Embedded
    private Seat seat;
    private Integer fixedPrice;
    @Enumerated(value = EnumType.STRING)
    private Grade grade;

    private Long performanceId;
    private Long memberTicketId;
    public void setPerformance(Long performanceId){
        this.performanceId = performanceId;
    }


    private Ticket(final Seat seat, final int fixedPrice,
                   final Grade grade) {
        this.seat = seat;
        this.fixedPrice = fixedPrice;
        this.grade = grade;
    }
    public static Ticket of(final Seat seat, final Integer fixedPrice,
                            final Grade grade){
        return new Ticket(seat, fixedPrice, grade);
    }
}
