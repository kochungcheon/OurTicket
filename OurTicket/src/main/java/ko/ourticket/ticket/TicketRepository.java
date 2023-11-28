package ko.ourticket.ticket;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	List<Ticket> findByPerformanceId(Long performanceId);

	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query(value = "SELECT t FROM Ticket t WHERE t.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
	Optional<Ticket> findByIdForUpdate(@Param("id") Long id);
}
