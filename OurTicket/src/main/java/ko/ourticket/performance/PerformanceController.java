package ko.ourticket.performance;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ko.ourticket.ticket.GradeCount;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PerformanceController {
	private final TicketCountFacadeService ticketCountFacadeService;

	@GetMapping("/api/shows/{performanceId}/availableSeat")
	public ResponseEntity<GradeCount> countTicketByGradeFor(@RequestParam Long performanceId) {
		GradeCount gradeCount = ticketCountFacadeService.countTicketByGradeForPerformance(performanceId);
		return ResponseEntity.ok(gradeCount);
	}
}
