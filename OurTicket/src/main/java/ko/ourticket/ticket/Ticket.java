package ko.ourticket.ticket;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
    private Long performanceId;
    private Long memberTicketId;
    public void setPerformance(Long performanceId){
        this.performanceId = performanceId;
    }

    public Grade getGrade(){
        return seat.getGrade();
    }
    private Ticket(final Seat seat, final int fixedPrice) {
        this.seat = seat;
        this.fixedPrice = fixedPrice;
    }
  
    public static Ticket of(final Seat seat, final Integer fixedPrice){
        return new Ticket(seat, fixedPrice);
    }
}
