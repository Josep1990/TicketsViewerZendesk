package zendesk.zccnewintern.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	public void init() {// fetch data and start application
		tickets.ticketPages(tickets.httpConnection());// fetch data and load constructor with tickets also create pages
														// with 25 tickes per page
		System.out.println("*** Application Started ***");
		mainMenu(); // start menu options1
	}

	public void mainMenu() {// the first menu that appears to the user

		int totalOfTickets = countElemetnsInList(); // get the number of tickets
		int firstId = ticketsList.get(0).get(0).getId();// first ticket id
		int lastId = ticketsList.get(ticketsList.size() - 1).get(ticketsList.get(ticketsList.size() - 1).size() - 1)
				.getId();// last ticket

		System.out.println("*** Welcome to the Ticket Viewer ***\n");
		System.out.println("************************************");
		System.out.println("Total of Tickets availabel: " + totalOfTickets);
		System.out.println("First Ticket id: " + firstId);
		System.out.println("Last Ticket id: " + lastId);
		System.out.println("************************************\n");

		startMainMenu();

	}

	// start main menu and validate input
	private void startMainMenu() {
		int exit = 1;
		String input = "";
		do {

			System.out.println("Please select one of the options bellow to navigate through the menu:\n");
			System.out.println("Type: \"1\" to view a list of all tickets.");
			System.out.println("Then navigate through the pages.\n");
			System.out.println("Type: \"2\" to view a single ticket.");
			System.out.println("Starting at the first ticket in the list.\n");
			System.out.println("Type: \"0\" or type \"exit\" to end the Application\n");
			System.out.println("Press \"Enter\" after select your option.");
			System.out.println("Enter your option:");
			try {

				input = sc.nextLine();

				if (input.matches("^[a-zA-Z0-9]*$")) {// checks if a the input is only alphanumeric characters

					if (input.equalsIgnoreCase("exit") || Integer.parseInt(input) == 0) {// end the application if input
																							// falls here
						System.out.println("Thank you for using this App, have a good one.");
						exit = 0;
						System.exit(exit);

					} else if (Integer.parseInt(input) == 1) {// go the list of tickes where u can see the pages

						ticketsMenuList();

					} else if (Integer.parseInt(input) == 2) {// selects a single ticket
						singleTicketMenu();
					} else {

						System.out.println("Let's have another look at the menu. Thank you...");
					}

				} else {
					System.out.println("We did expect this, but Select one option from the menu, please.\n");
				}

			} catch (Exception e) {

				System.out.println("Something didn't go well here... Let's try again...");

			}
		} while (exit != 0);

	}

	// inialize the Ticket List Menu view
	private void ticketsMenuList() {
		String input = "";
		int exit = 1;
		int page;
		System.out.println("\nYou selected the List Ticket Menu.");
		do {

			showPages();
			System.out.println("\nType: \"back\" to go back to the Main menu.");
			System.out.println("Type: \"0\" or type \"exit\" to end the Application.");
			try {// dont end the app when a invalid input
				input = sc.nextLine();
				if (input.matches("^[a-zA-Z0-9]*$")) {// checks if a the input is only alphanumeric characters

					if (input.equalsIgnoreCase("back")) {// go back to main menu
						mainMenu();

					} else if (input.equalsIgnoreCase("exit") || Integer.parseInt(input) == 0) { // end the application
						System.out.println("Thank you for using this App, have a good one.");
						exit = 0;
						System.exit(exit);
					} else { // navigate through the pages
						page = Integer.parseInt(input) - 1;
						printTicketsList(page);
					}
				} else {// print a message if the user input a special caracter
					System.out.println("We did expect this, but Select one option from the menu, please.\n");
				}
			} catch (Exception e) {// print and restart the menu if the user type a invalid input

				System.out.println("Something didn't go well here... Let's try again...");
			}
		} while (exit != 0);

	}

	// print the tickets per page and validate the input
	private void printTicketsList(int pageNumber) {

		if (pageNumber >= 0 && pageNumber < ticketsList.size()) {
			System.out.println(ticketsList.get(pageNumber));
			System.out.println("\n*** END OF THE TICKETS LIST ***\n");
		} else {
			System.out.println("I am sorry, I could not understant which page you looking for.");
			System.out.println("Please, select one of the pages listed on the menu.");
		}
	}

	// show the number of pages in the command line
	private void showPages() {

		System.out.println("Select the page that you would like to see.");
		for (int i = 0; i < ticketsList.size(); i++) {
			System.out.print("Page: " + (i + 1) + ", ");
		}
		System.out.println();

	}

	// count the number of tickets in the list
	private int countElemetnsInList() {

		int size = 0;
		for (int i = 0; i < ticketsList.size(); i++) {
			size += ticketsList.get(i).size();
		}
		return size;
	}

	private void singleTicketMenu() {
		
		String input = "";
		int exit = 1;
		int targetId;
		System.out.println("\nYou selected the Single Ticket Menu.");
		do {

			showTicketsIds();
			System.out.println("Select the Id of the ticket that you would like to see.");
			System.out.println("\nType: \"back\" to go back to the Main menu.");
			System.out.println("Type: \"0\" or type \"exit\" to end the Application.");
			try {// dont end the app when a invalid input
				input = sc.nextLine();
				if (input.matches("^[a-zA-Z0-9]*$")) {// checks if a the input is only alphanumeric characters

					if (input.equalsIgnoreCase("back")) {// go back to main menu
						mainMenu();

					} else if (input.equalsIgnoreCase("exit") || Integer.parseInt(input) == 0) { // end the application
						System.out.println("Thank you for using this App, have a good one.");
						exit = 0;
						System.exit(exit);
					} else { // navigate through the pages
						
						targetId = Integer.parseInt(input);
						if(getTicket(targetId) == null) {
							
							System.out.println("Ticket Id Not Found.");
							System.out.println("Please, check the Id list for references\n");
							
						}else {
							System.out.println("*** Ticket requested ***");
							System.out.println(getTicket(targetId));
						}
					}
				} else {// print a message if the user input a special caracter
					System.out.println("We did expect this, but Select one option from the menu, please.\n");
				}
			} catch (Exception e) {// print and restart the menu if the user type a invalid input

				System.out.println("Something didn't go well here... Let's try again...");
			}
		} while (exit != 0);

	}

	private void showTicketsIds() {
		System.out.println("*** Tickets Id's List ***\n");
		for (ArrayList<Tickets> innerList : ticketsList) {

			for (Tickets ticket : innerList) {

				System.out.print(ticket.getId() + ", ");
			}
			System.out.println("\n");
		}

	}

	// print the tickets per page and validate the input
	private Tickets getTicket(int targetId) {

			for (ArrayList<Tickets> innerList : ticketsList) {

				for (Tickets ticket : innerList) {

					if (ticket.getId() == targetId) {						
						
						return ticket;
					}
				}

			}
			return null;
		
	}

}
