package zendesk.zccnewintern.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import zendesk.zccnewintern.controller.TicketsController;
import zendesk.zccnewintern.model.Tickets;

public class TicketsView {
	
	private TicketsController tickets;
	private List<ArrayList<Tickets>> ticketsList;
	
	private Scanner sc = new Scanner(System.in);
	
	
	public TicketsView() {
		
		this.tickets = new TicketsController();
		this.ticketsList = tickets.ticketsList;
	}

	public void init() {		
		
		tickets.ticketPages(tickets.httpConnection());//fetch data and load constructor with tickets also create pages with 25 tickes per page
		int totalOfTickets = countElemetnsInList(); //get the number of tickets
		int firstId = ticketsList.get(0).get(0).getId();//first ticket id
		int lastId = ticketsList.get(ticketsList.size() - 1).get(ticketsList.get(ticketsList.size() - 1).size() - 1).getId();
		
		System.out.println("*** Application Started ***");		
		System.out.println("*** Welcome to the Ticket Viewer ***\n");
		System.out.println("************************************");
		System.out.println("Total of Tickets availabel: " + totalOfTickets );
		System.out.println("First Ticket id: " + firstId );
		System.out.println("Last Ticket id: " + lastId);
		System.out.println("************************************\n");
		
		System.out.println("Please select one of the options bellow to navigate through the menu:\n");
		System.out.println("Press: \"1\" to view a list of all the tickets.");
		System.out.println("Starting at the first page of the list.\n");
		
		System.out.println("Press: \"2\" to view a single ticket.");
		System.out.println("Starting at the first ticket in the list.\n");
		
		System.out.println("Press: \"0\" to end the Application\n");
		System.out.println("Enter your option:");

		int input = sc.nextInt();
		
		if(input == 1) {
			
			printTickets();
			
		}else if(input == 2) {
			
			
		}else if(input == 0) {
			
			System.out.println("Thank you for using this App, have a good one.");
			System.exit(0);
		}
		

		
	}
	
	private void printTickets() {
	
		
		//System.out.println(ticketsList.get(0));
		for(ArrayList<Tickets> x :ticketsList ) {
			
			
			System.out.println(x);
			
			for(Tickets y : x) {
				
				
				
			}
		}
		
	}
	
	private int countElemetnsInList() {		
		int size = 0;		
		for(int i = 0; i < ticketsList.size(); i++) {			
			size += ticketsList.get(i).size();			
		}
		return size;	
	}

}
