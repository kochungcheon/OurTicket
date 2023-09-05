package ko.ourticket.ticket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
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
    public void setPerformance(Long performanceId){
        this.performanceId = performanceId;
    }
    protected Ticket(){}

    private Ticket(final Integer seatCount, final Integer fixedPrice,
                   final Grade grade) {
        this.seatCount = seatCount;
        this.fixedPrice = fixedPrice;
        this.grade = grade;
    }
    public static Ticket of(final Integer seatCount, final Integer fixedPrice,
                            final Grade grade){
        return new Ticket(seatCount, fixedPrice, grade);
    }
}
