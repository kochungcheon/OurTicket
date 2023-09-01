package Ko.OurTicket.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public record GradeCount(Map<Grade, Integer> gradeCountMap) {
    public GradeCount(Map<Grade, Integer> gradeCountMap) {
        this.gradeCountMap = gradeCountMap;
    }
}
