package ko.ourticket.ticket;

public enum Grade {
	VIP("최고 등급 좌석"),
	R("중간 등급 좌석"),
	S("일반 등급 좌석");

	private final String description;

	Grade(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
