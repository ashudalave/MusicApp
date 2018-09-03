package cs636.music.presentation.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs636.music.domain.Product;
import cs636.music.domain.User;
import cs636.music.service.CatalogServiceAPI;
import cs636.music.service.SalesServiceAPI;
import cs636.music.service.ServiceException;

public class ListenController implements Controller {
	private SalesServiceAPI salesService;
	private CatalogServiceAPI catalogService;
	private String view;

	public ListenController(SalesServiceAPI salesService, CatalogServiceAPI catalogS, String view) {
		this.salesService = salesService;
		this.catalogService = catalogS;
		this.view = view;
	}

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("user");
		
		Product product = userBean.getProduct();
		User user = userBean.getUser();
		
		request.setAttribute("product", product);

		// The user registered just before getting to this page - create user
		if (user == null) {
			try {
				String firstName = (String) request.getParameter("firstName");
				String lastName = (String) request.getParameter("lastName");
				String email = (String) request.getParameter("email");
				salesService.registerUser(firstName, lastName, email);
				System.out.println("fname: "+firstName);
				System.out.println("lastName: "+lastName);
				System.out.println("email: "+email);
				
			
				session.setAttribute("user", userBean);
				System.out.println("user registered");
				userBean.setUser(user);
				
				
			} catch (ServiceException e) {
				System.out.println("ListenController: " + e);
				throw new ServletException(e);
			}
		}
		System.out.println("Returning from ListenController");
		return view;
	}
}
