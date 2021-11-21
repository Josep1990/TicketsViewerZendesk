package zendesk.zccnewintern.model;

public class Tickets {
	
	private int id;
	private String subject;
	private String description;
	
	public Tickets(int id, String subject, String description) {
		this.id = id;
		this.subject = subject;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public String getDescription() {
		return description;
	}
	

}
