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
		tickets.ticketPages(tickets.httpConnection());// fetch data and load constructor with tickets also create pages
														// with 25 tickes per page
		System.out.println("*** Application Started ***");
		mainMenu(); // start menu options
	}

	private void mainMenu() {

		int totalOfTickets = countElemetnsInList(); // get the number of tickets
		int firstId = ticketsList.get(0).get(0).getId();// first ticket id
		int lastId = ticketsList.get(ticketsList.size() - 1).get(ticketsList.get(ticketsList.size() - 1).size() - 1)
				.getId();

		System.out.println("*** Welcome to the Ticket Viewer ***\n");
		System.out.println("************************************");
		System.out.println("Total of Tickets availabel: " + totalOfTickets);
		System.out.println("First Ticket id: " + firstId);
		System.out.println("Last Ticket id: " + lastId);
		System.out.println("************************************\n");

		System.out.println("Please select one of the options bellow to navigate through the menu:\n");
		System.out.println("Type: \"1\" to view a list of all tickets.");
		System.out.println("Then navigate through the pages.\n");

		System.out.println("Type: \"2\" to view a single ticket.");
		System.out.println("Starting at the first ticket in the list.\n");

		System.out.println("Type: \"0\" or type \"exit\" to end the Application\n");
		System.out.println("Enter your option:");

		String input = sc.nextLine();

		if (input.equalsIgnoreCase("exit") || Integer.parseInt(input) == 0) {
			System.out.println("Thank you for using this App, have a good one.");
			System.exit(0);
		} else if (Integer.parseInt(input) == 1) {

			ticketsMenuList();

		} else if (Integer.parseInt(input) == 2) {

		}

	}

	private void ticketsMenuList() {
		String input = "";
		int exit = 1;
		int page;
		System.out.println("\nYou selected the List Ticket Menu.");
		do {

			showPages();				
			System.out.println("\nType: \"back\" to go back to the Main menu.");
			System.out.println("Type: \"0\" or type \"exit\" to end the Application.");
			input = sc.nextLine();
			
			if (input.equalsIgnoreCase("back")) {
				mainMenu();
			} else if (input.equalsIgnoreCase("exit") || Integer.parseInt(input) == 0) {
				System.out.println("Thank you for using this App, have a good one.");
				exit = 0;
				System.exit(exit);
			}else {
				page = Integer.parseInt(input) - 1;
				printTicketsList(page);				
			}


		} while (exit != 0);

	}

	private void printTicketsList(int pageNumber) {
		
		if (pageNumber >= 0 && pageNumber < ticketsList.size()) {
			System.out.println(ticketsList.get(pageNumber));
			System.out.println("\n*** END OF THE TICKETS LIST ***");
		}else {
			System.out.println("I am sorry, I could not understant which page you looking for.");
			System.out.println("Please, select one of the pages listed on the menu.");
		}
	}
	
	private void showPages() {
//		String input = "";
//		int page;
		System.out.println("Select the page that you would like to see.");
		for (int i = 0; i < ticketsList.size(); i++) {
			System.out.print("Page: " + (i + 1) + ", ");
		}
		System.out.println();
//		input = sc.nextLine();
//		page = Integer.parseInt(input) - 1;
		//printTicketsList(page);
		
	}

	private int countElemetnsInList() {
		int size = 0;
		for (int i = 0; i < ticketsList.size(); i++) {
			size += ticketsList.get(i).size();
		}
		return size;
	}

}
