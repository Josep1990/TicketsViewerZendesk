package zendesk.zccnewintern;

import zendesk.zccnewintern.controller.TicketsController;
import zendesk.zccnewintern.view.TicketsView;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TicketsController tk = new TicketsController();
		TicketsView tv = new TicketsView();
		
		//tk.httpConnection();
	//	tk.ticketPages(tk.httpConnection());
	//	System.out.println(tk.ticketsList.isEmpty());
		
		tv.init();
		


	}

}
