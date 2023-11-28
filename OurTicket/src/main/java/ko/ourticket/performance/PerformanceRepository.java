package ko.ourticket.performance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

	Page<Performance> findByNameContaining(String name, Pageable pageable);

}
