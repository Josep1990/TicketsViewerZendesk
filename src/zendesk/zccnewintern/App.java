package zendesk.zccnewintern;

import zendesk.zccnewintern.controller.TicketsController;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TicketsController tk = new TicketsController();
		
		tk.httpConnection();

	}

}
