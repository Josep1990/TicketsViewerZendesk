package unittest.zendesk.zccnewintern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zendesk.zccnewintern.view.TicketsView;

class TicketsViewTest {
	
	private TicketsView tView;
	
	@BeforeEach
	void setUp() {
		tView = new TicketsView();	
		
	}
	
	@Test
	void isViewNotNull() {
		assertNotNull("View Obj is null.", tView);
	}

	@Test
	void canUseTickets() {		
		
		assertNotNull("2D ArrayList from the View Controller is null.", tView.ticketsList);
	}
	
	@Test
	void isTicketsNotEmpty() {		
		assertFalse("2D ArrayList from the View Controller is empty.", tView.ticketsList.isEmpty());
	}

}
