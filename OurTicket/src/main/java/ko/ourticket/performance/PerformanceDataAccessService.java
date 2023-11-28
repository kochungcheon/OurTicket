package ko.ourticket.performance;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PerformanceDataAccessService implements PerformanceReader {
	private final PerformanceRepository performanceRepository;

	public Performance findBy(Long performanceId) {
		return performanceRepository.findById(performanceId).orElseThrow(
			() -> new IllegalArgumentException()
		);
	}
}
