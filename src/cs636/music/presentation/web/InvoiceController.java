package cs636.music.presentation.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs636.music.domain.Cart;
import cs636.music.domain.Invoice;
import cs636.music.domain.User;
import cs636.music.service.CatalogServiceAPI;
import cs636.music.service.SalesServiceAPI;
import cs636.music.service.ServiceException;

import cs636.music.service.data.InvoiceData;

public class InvoiceController implements Controller {
	private CatalogServiceAPI catalogService;
	private SalesServiceAPI salesService;
	private String view;


	public InvoiceController(SalesServiceAPI salesService, CatalogServiceAPI catalogS, String view) {
		this.salesService = salesService;
		this.catalogService = catalogS;
		this.view = view;
	}

	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("user");
		Cart userCart = userBean.getCart();
		User user = userBean.getUser();
		InvoiceData invoice = null;
		try {
			// The user registered just before getting to this page - create user
			if (user == null) {
				String firstName = (String) request.getParameter("firstName");
				String lastName = (String) request.getParameter("lastName");
				String email = (String) request.getParameter("email");
				salesService.registerUser(firstName, lastName, email);
				user.setFirstname(firstName);
				user.setLastname(lastName);
				user.setEmailAddress(email);
				userBean.setUser(user);
			}
			invoice = salesService.checkout(userCart, user.getId());
		} catch (ServiceException e) {
			System.out.println("InvoiceController: " + e);
			throw new ServletException(e);
		}
		request.setAttribute("invoice", invoice);
		session.setAttribute("user", userBean);		
		return view;
	}
}
