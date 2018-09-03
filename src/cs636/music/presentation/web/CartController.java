package cs636.music.presentation.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs636.music.domain.Cart;
import cs636.music.domain.CartItem;
import cs636.music.domain.Product;
import cs636.music.service.CatalogServiceAPI;
import cs636.music.service.SalesServiceAPI;
import cs636.music.service.ServiceException;
import cs636.music.service.data.CartItemData;

public class CartController implements Controller {
	private SalesServiceAPI salesService;
	private CatalogServiceAPI catalogService;
	private String view;

	public CartController(SalesServiceAPI salesService, CatalogServiceAPI catalogS, String view) {
		this.salesService = salesService;
		this.catalogService = catalogS;
		this.view = view;
	}

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		String requestURI = request.getRequestURI();
		System.out.println(" -- inside handle request of cart controller -- ");
		UserBean userBean = (UserBean) session.getAttribute("user");
		Cart userCart = userBean.getCart();
		CartItem cartItem = null;
		Product product = userBean.getProduct();
		Set<CartItemData> cartInfo =null;
		boolean flag =false;
		String addItem = request.getParameter("addItem");
		// creating a new user cart
		if (userCart == null) {
			userCart = catalogService.createCart();
		}
		try {
			cartInfo = catalogService.getCartInfo(userCart);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (request.getParameter("addItem")!=null ) {
			flag=true;	
			
			// Add new product to cart, if there is one
			if (flag && product != null) {
				catalogService.addItemtoCart(product.getId(), userCart, 1);
				
			}
			try {
				cartInfo = catalogService.getCartInfo(userCart);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(requestURI.endsWith("/updateItem")) {
			int updatequantity =Integer.parseInt(request.getParameter("quantity"));
			catalogService.changeCart(product.getId(), userCart, updatequantity);
			try {
				cartInfo = catalogService.getCartInfo(userCart);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(requestURI.endsWith("/removeItem")) {
			int updatequantity =Integer.parseInt(request.getParameter("quantity"));
			catalogService.removeCartItem(product.getId(), userCart);
			try {
				cartInfo = catalogService.getCartInfo(userCart);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String invoiceNextUrl = userBean.getUser() == null ? "register.html?checkout=true" : "invoice.html";
			
		request.setAttribute("invoiceNextUrl", invoiceNextUrl);
		request.setAttribute("cartInfo", cartInfo);
		request.setAttribute("product", product);
		request.setAttribute("cart", userCart);
		userBean.setCart(userCart);
		userBean.setProduct(product);
		session.setAttribute("user", userBean);		
		return view;
	}
	
	}
