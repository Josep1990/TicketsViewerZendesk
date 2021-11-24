package zendesk.zccnewintern.model;

public class Tickets {
	
	private int id;
	private String subject;
	private String description;
	
	//ticket constructor
	public Tickets(int id, String subject, String description) {
		this.id = id;
		this.subject = subject;
		String reducedDescription = description.substring(0, (description.indexOf(".") + 1));		
		this.description = reducedDescription;
	}
//***************************************************************
// getters and setters and toString Method
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
		this.description = description;
	}

	@Override
	public String toString() {
		return "\nTicket Id: " + id + "\nSubject: " + subject + "\nDescription: " + description + "\n";
	}
	

}
