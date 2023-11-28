package ko.ourticket.ticket;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {
	private Integer seatCount;
	@Enumerated(value = EnumType.STRING)
	private Grade grade;

	private Seat(Integer seatCount, Grade grade) {
		this.seatCount = seatCount;
		this.grade = grade;
	}

	public Grade getGrade() {
		return this.grade;
	}

	public static Seat of(final Integer seatCount, final Grade grade) {
		return new Seat(seatCount, grade);
	}

	public void reserveSeat(final Integer requestCount) {
		if (this.seatCount < requestCount) {
			throw new RuntimeException("좌석이 부족합니다.");
		}
		Integer seatCount = this.seatCount - requestCount;
		this.seatCount = seatCount;
	}
}
