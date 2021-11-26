package unittest.zendesk.zccnewintern;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zendesk.zccnewintern.controller.TicketsController;
import zendesk.zccnewintern.model.Tickets;

class TicketsControllerTest {
	
	private TicketsController testController;
	private ArrayList<Tickets> ticketsList;
	
	@BeforeEach
	void setUp() {
		testController = new TicketsController();
		ticketsList = testController.httpConnection(); //fetch data and return tickets 	
	}
	
	@Test
	void canGetAllTickets() {
		//test if the tickets are beign returned in the right data format
		
		//given		
		testController.ticketPages(ticketsList); //adds tickets to the pages 
		
		//when
		List<ArrayList<Tickets>> ticketsPages = testController.ticketsList;
		
		//then		
		assertFalse("List is empty", ticketsPages.isEmpty());

	}
	
	
}
