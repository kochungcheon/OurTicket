package ko.ourticket.check;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
	@GetMapping("/health")
	public String test() {
		return "hello";
	}
}
