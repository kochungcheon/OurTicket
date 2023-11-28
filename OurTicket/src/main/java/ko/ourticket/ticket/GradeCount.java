package ko.ourticket.ticket;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record GradeCount(Map<Grade, Integer> gradeCountMap) {
	public static GradeCount of(Map<Grade, Integer> values) {
		return new GradeCount(values);
	}

	public static GradeCount from(List<Ticket> tickets) {
		Map<Grade, Integer> result = tickets.stream()
			.collect(Collectors.groupingBy(
				Ticket::getGrade,
				() -> new EnumMap<Grade, Integer>(Grade.class),
				Collectors.summingInt(ticket -> ticket.getSeat().getSeatCount())
			));
		return new GradeCount(result);
	}
}
