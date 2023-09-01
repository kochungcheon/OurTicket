package ko.ourticket.Ticket;

import ko.ourticket.performance.Performance;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity @Getter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    private int seatCount;
    private int fixedPrice;
    @Enumerated(value = EnumType.STRING)
    private Grade grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performance performance;
    public void setPerformance(Performance performance){
        this.performance = performance;
    }
    protected Ticket(){}

    private Ticket(final int seatCount, final int fixedPrice, final Grade grade) {
        this.seatCount = seatCount;
        this.fixedPrice = fixedPrice;
        this.grade = grade;
    }
    public static Ticket of(final int seatCount, final int fixedPrice, final Grade grade){
        return new Ticket(seatCount, fixedPrice, grade);
    }
}
