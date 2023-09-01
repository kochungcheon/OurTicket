package Ko.OurTicket.performance;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class PerformanceDateTime {
    private LocalDateTime performanceStartDate;
    private LocalDateTime performanceEndDate;

    public PerformanceDateTime(final LocalDateTime performanceStartDate, final LocalDateTime performanceEndDate) {
        this.performanceStartDate = performanceStartDate;
        this.performanceEndDate = performanceEndDate;
    }

    public static PerformanceDateTime of(final LocalDateTime performanceStartDate, final LocalDateTime performanceEndDate){
        return new PerformanceDateTime(performanceStartDate, performanceEndDate);
    }
}
