package ko.ourticket.ticket;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {
    private Integer count;
    @Enumerated(value = EnumType.STRING)
    private Grade grade;
    private Seat(Integer count, Grade grade){
        this.count = count;
        this.grade = grade;
    }
    public Grade getGrade(){
        return this.grade;
    }
    public static Seat of(final Integer count, final Grade grade){
        return new Seat(count, grade);
    }
    public void reserveSeat(final Integer requestCount){
        if (this.count < requestCount){
            throw new RuntimeException("좌석이 부족합니다.");
        }
        Integer seatCount = this.count - requestCount;
        this.count = seatCount;
    }
}
