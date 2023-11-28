package ko.ourticket.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
	USER_ALREADY_EXIST("이미 존재하는 Username 입니다."),
	INVALID_SIGNUP_REQUEST("비정상적인 요청입니다."),
	CANNOT_FOUND_PERFORMANCE_DETAIL("해당 공연정보를 찾을 수 없습니다.");
	private final String description;

	ExceptionCode(String description) {
		this.description = description;
	}
}
