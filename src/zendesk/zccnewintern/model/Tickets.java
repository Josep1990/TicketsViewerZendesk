package zendesk.zccnewintern.model;

public class Tickets {
	
	private int id;
	private String subject;
	private String description;
	
	public Tickets(int id, String subject, String description) {
		this.id = id;
		this.subject = subject;
		String reducedDescription = description.substring(0, (description.indexOf(".") + 1));		
		this.description = reducedDescription;
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
	

	public void setDescription(String description) {
		String reducedDescription = description.substring(0,150);
		this.description = reducedDescription;
	}

	@Override
	public String toString() {
		return "\nTicket Id: " + id + "\nSubject: " + subject + "\nDescription: " + description + "\n";
	}
	

}
