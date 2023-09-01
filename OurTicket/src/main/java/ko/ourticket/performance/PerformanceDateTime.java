package ko.ourticket.performance;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class PerformanceDateTime {
    private LocalDateTime performanceStartDateTime;
    private LocalDateTime performanceEndDateTimeTime;

    public PerformanceDateTime(final LocalDateTime performanceStartDate, final LocalDateTime performanceEndDateTime) {
        this.performanceStartDateTime = performanceStartDate;
        this.performanceEndDateTimeTime = performanceEndDateTime;
    }

    public static PerformanceDateTime of(final LocalDateTime performanceStartDate, final LocalDateTime performanceEndDateTime){
        return new PerformanceDateTime(performanceStartDate, performanceEndDateTime);
    }
}
