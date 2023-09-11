package ko.ourticket.ticket;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {
    private Integer seatCount;
    private Seat(Integer seatCount){
        this.seatCount = seatCount;
    }
    public static Seat of(final Integer seatCount){
        return new Seat(seatCount);
    }
    public Seat reserveSeat(final Integer requestCount){
        if (this.seatCount < requestCount){
            throw new RuntimeException("좌석이 부족합니다.");
        }
        Integer seatCount = this.seatCount - requestCount;
        return new Seat(seatCount);
    }
}
