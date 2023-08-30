package Ko.OurTicket.Performance;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class PerformanceDate {
    private LocalDateTime performanceStartDate;
    private LocalDateTime performanceEndDate;

    public PerformanceDate(final LocalDateTime performanceStartDate, final LocalDateTime performanceEndDate) {
        this.performanceStartDate = performanceStartDate;
        this.performanceEndDate = performanceEndDate;
    }

    public static PerformanceDate of(final LocalDateTime performanceStartDate, final LocalDateTime performanceEndDate){
        return new PerformanceDate(performanceStartDate, performanceEndDate);
    }
}
