package ko.ourticket.Ticket;

import java.util.Map;

public record GradeCount(Map<Grade, Integer> gradeCountMap) {
    public static GradeCount of(Map<Grade, Integer> gradeCountMap) {
        return new GradeCount(gradeCountMap);
    }
}
