package cs636.music.presentation.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs636.music.service.SalesServiceAPI;

public class RegistrationController implements Controller {
	private SalesServiceAPI salesService;
	private String view;

	public RegistrationController(SalesServiceAPI salesService, String view) {
		this.salesService = salesService;
		this.view = view;
		System.out.println("this.salaService in rcontroller" +salesService);
	}

	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// Forward to either invoice page or listen page
		boolean checkout = request.getParameter("checkout") != null;
		System.out.println("checkout"+checkout);
		String nextPageURL = checkout ? "invoice.html" : "listen.html";
		
		request.setAttribute("nextPageURL", nextPageURL);
		System.out.println("nextPageURL"+nextPageURL);
		
		return view;
	}
}
