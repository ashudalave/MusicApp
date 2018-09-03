package cs636.music.presentation.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;

import cs636.music.domain.*;
//import cs636.music.data.*;
//import cs636.music.util.CookieUtil;
import cs636.music.service.CatalogService;
import cs636.music.service.CatalogServiceAPI;
import cs636.music.service.SalesServiceAPI;

public class CatalogController implements Controller {
    private CatalogServiceAPI catalogService;
    private SalesServiceAPI salesService;
    private String view;
    
    public CatalogController(SalesServiceAPI salesService, CatalogServiceAPI catalogS, String view) {
		this.salesService = salesService;
		this.catalogService = catalogS;
		this.view = view;
	}
    
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Product> products = new ArrayList<Product>();
		try {
			products = new ArrayList<Product>(catalogService.getProductList());
		} catch (Exception e) {
			System.out.println("CatalogController: " + e);
			throw new ServletException(e);
		}
		request.setAttribute("products", products);
		return view;
	}
    
}