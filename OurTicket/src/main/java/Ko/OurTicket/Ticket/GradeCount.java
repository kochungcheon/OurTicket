package Ko.OurTicket.Ticket;

import java.util.Map;

public record GradeCount(Map<Grade, Integer> gradeCountMap) {
    public GradeCount(Map<Grade, Integer> gradeCountMap) {
        this.gradeCountMap = gradeCountMap;
    }
}
