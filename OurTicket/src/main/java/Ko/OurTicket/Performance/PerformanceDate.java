package Ko.OurTicket.Performance;

import jakarta.persistence.Embeddable;

@Embeddable
public class PerformanceDate {
    private String PerformanceStartDate;
    private String PerformanceEndDate;

    private PerformanceDate(final String performanceStartDate, final String performanceEndDate) {
        PerformanceStartDate = performanceStartDate;
        PerformanceEndDate = performanceEndDate;
    }
    public static PerformanceDate of(final String performanceStartDate, final String performanceEndDate){
        return new PerformanceDate(performanceStartDate, performanceEndDate);
    }
}
