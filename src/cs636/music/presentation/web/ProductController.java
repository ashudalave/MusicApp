package cs636.music.presentation.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs636.music.domain.Product;
import cs636.music.service.CatalogServiceAPI;
import cs636.music.service.SalesServiceAPI;
import cs636.music.service.ServiceException;


public class ProductController implements Controller {
	private SalesServiceAPI salesService;
	private CatalogServiceAPI catalogService;
	private String view;

	public ProductController(SalesServiceAPI salesService, CatalogServiceAPI catalogS, String view) {
		this.salesService = salesService;
		this.catalogService = catalogS;
		this.view = view;
	}

	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("user");
		
		Product product = null;
		String productCode = (String) request.getParameter("productCode");
		
		
		
		String listenNextUrl = userBean.getUser() == null ? "register.html" : "listen.html";
		
		//TO DELETE
				System.out.println( " Inside Product Controller " + productCode);
				System.out.println( " Iuct Controller " +  listenNextUrl);		
		
		if (productCode != null && !productCode.equals("")) {
			try {
				product = catalogService.getProductByCode(productCode);
				userBean.setProduct(product);
			} catch (ServiceException e) {
				System.out.println("ProductController: " + e);
				throw new ServletException(e);
			}
		}
		request.setAttribute("listenNextUrl", listenNextUrl);
		request.setAttribute("product", product);
		session.setAttribute("user", userBean);
		
		return view;
	}
}
