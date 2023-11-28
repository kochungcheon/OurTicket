package ko.ourticket.performance;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Performance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;
	private String name;
	private String description;
	@Embedded
	private PerformanceDateTime performanceDateTime;

	@ElementCollection
	private final List<Long> ticketIds = new ArrayList<>();

	protected Performance() {
	}

	private Performance(final String name, final String description,
		final PerformanceDateTime performanceDateTime) {
		this.name = name;
		this.description = description;
		this.performanceDateTime = performanceDateTime;
	}

	public static Performance of(final String name, final String description,
		final PerformanceDateTime performanceDateTime) {
		return new Performance(name, description, performanceDateTime);
	}

	public void addTicketId(Long ticketId) {
		this.ticketIds.add(ticketId);
	}
}
