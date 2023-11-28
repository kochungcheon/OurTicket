package ko.ourticket.exception;

import lombok.Getter;

@Getter
public class ExceptionHandler extends RuntimeException {
	ExceptionCode code;
	String message;

	public ExceptionHandler(ExceptionCode code, String message) {
		this.code = code;
		this.message = message;
	}
}
