package cs636.music.presentation.web;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs636.music.service.CatalogServiceAPI;
import cs636.music.service.SalesServiceAPI;

import cs636.music.config.MusicSystemConfig;
import cs636.music.presentation.client.AdminApp;
import cs636.music.service.ServiceException;
import cs636.music.service.data.DownloadData;
import cs636.music.service.data.InvoiceData;

// like Spring MVC DispatcherServlet and its config file, but simpler.
// This servlet is handling the student pages of the pizza project,
// calling on various controllers, roughly one for each student page or form.
// Note that all the jsp filenames (for views) are in this file, not
// in the controllers themselves.  Each controller is set up
// here and given its forward-to URLs in its constructor.

public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SalesServiceAPI salesService;
	private CatalogServiceAPI catalogService; // for Downloads
	
	
	private static final String ADMIN_JSP_DIR = "/WEB-INF/admin/adminWelcome.jsp";

	// The controllers, roughly one per student page or form
	/*
	 * private static Controller studentWelcomeController; private static Controller
	 * orderReceiveController; private static Controller orderFormController;
	 * private static Controller orderPizzaController;
	 */
	// String constants for URL's

	private static final String SITE_WELCOME_URL = "/WEB-INF/welcome.html";
	private static final String SITE_WELCOME_VIEW = "/welcome.jsp";
	private static final String STUDENT_WELCOME_URL = "/WEB-INF/jsp/StudentWelcome.jsp";
//	private static final String USER_WELCOME_URL = "/WEB-INF/jsp/userWelcome.jsp";
	private static final String CART_URL = "/WEB-INF/jsp/cart.jsp";
	private static final String CATALOG_URL = "/WEB-INF/jsp/catalog.jsp";
	private static final String INVOICE_URL = "/WEB-INF/jsp/invoice.jsp";
	private static final String SOUND_URL = "/WEB-INF/jsp/sound.jsp";
	private static final String PRODUCT_URL = "/WEB-INF/jsp/product.jsp";
	private static final String REGISTER_URL = "/WEB-INF/jsp/register.jsp";

	// Admin pages (incomplete: see music3 for full admin pages)
	private static final String INIT_DB_URL = "/WEB-INF/jsp/initdb.jsp";
	//private static final String PROCESS_INVOICES_URL = "/WEB-INF/jsp/processInvoices.jsp";
	
	
	
	

	// The controllers, one per user page
	private static Controller studentWelcomeController;
	private static Controller cartController;
	private static Controller catalogController;
	private static Controller invoiceController;
	private static Controller listenController;
	private static Controller downloadController;
	private static Controller productController;
	private static Controller registrationController;
	
	private static Controller initializeDatabaseController;
	//private static Controller processInvoiceController;
	

	// // the order form submits to the following URL
	// private static final String ORDER_PIZZA_URL = "/orderPizza.html";
	// // a successful pizza order is followed by a redirect to the this URL:
	// private static final String ORDER_REDIRECT_URL = "/studentWelcome.html";
	//
	// Initialization of servlet: runs before any request is
	// handled in the web app. It does MusicSystemConfig initialization
	// then sets up all the controllers
	@Override
	public void init() throws ServletException {
		System.out.println("Starting dispatcher servlet initialization");
		// read file for db to use
		String salesDbName = "hsql";
		String catalogDbName = "hsql";
		
		//String dbName = "hsql";
		// If configureServices fails, it logs errors to the tomcat log,
		// then throws (not caught here), notifying tomcat of its failure,
		// so tomcat won't allow any requests to be processed
		
		try {
			MusicSystemConfig.configureCatalogService(catalogDbName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			MusicSystemConfig.configureSalesService(salesDbName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catalogService = MusicSystemConfig.getCatalogService();
		salesService = MusicSystemConfig.getSalesService();
		
		// list of all the controllers and their forward URLs
		studentWelcomeController = new StudentWelcomeController(STUDENT_WELCOME_URL);
		cartController = new CartController(salesService, catalogService, CART_URL);
		catalogController = new CatalogController(salesService, catalogService, CATALOG_URL);
		invoiceController = new InvoiceController(salesService, catalogService, INVOICE_URL);
		listenController = new ListenController(salesService, catalogService, SOUND_URL);
		downloadController = new DownloadController(salesService, catalogService); //  URL to be computed
		productController = new ProductController(salesService, catalogService, PRODUCT_URL);
		registrationController = new RegistrationController(salesService, REGISTER_URL);

		initializeDatabaseController = new InitializeDatabaseController(catalogService, INIT_DB_URL);
		//processInvoiceController = new ProcessInvoiceController(salesService, PROCESS_INVOICES_URL);
		
	}

	// Called when app server is shutting this servlet down
	// because it is shutting the app down.
	// Since this servlet is in charge of this app, it is
	// the one to respond by shutting down the BL+DAO
	// (the SysTestServlet ignores the shutdown)
	@Override
	public void destroy() {
		System.out.println("DispatcherServlet: shutting down");
		MusicSystemConfig.shutdownServices(); //does nothing bascially 
		// PizzaSystemConfig.shutdownServices();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURL = request.getServletPath(); 
		String requestURI = request.getRequestURI();
		System.out.println("DispatcherServlet: requestURL = " + requestURL + " " + requestURI);
		// At userWelcome, the user gets a UserBean.
		// If it's not there for subsequent pages, hand the request to
		// studentWelcome. Having the bean is like being "logged in".
		boolean hasBean = (request.getSession().getAttribute("user") != null);

		// Dispatch to the appropriate Controller, which will determine
		// the next URL to use as well as do its own actions.
		// The URL returned by handleRequest will be a servlet-relative URL, 
		// like /WEB-INF/jsp/foo.jsp (a view) 
		// or /something.html (for a controller).
		// Note that although resources below /WEB-INF are inaccessible
		// to direct HTTP requests, they are accessible to forwards
		String forwardURL = null; 
		if (requestURI.contains(SITE_WELCOME_URL) || requestURI.contains("/welcome.html"))
			forwardURL = SITE_WELCOME_VIEW; // no controller needed
		else if (requestURI.contains("/adminWelcome.html")) {
			forwardURL = ADMIN_JSP_DIR; // no controller needed
		}
		else if (requestURI.contains("/initdb.html"))
			forwardURL = initializeDatabaseController.handleRequest(request, response);
		/*else if (requestURI.contains("/processInvoices.html"))
			forwardURL = processInvoiceController.handleRequest(request, response);*/
	    // For non-admin pages, test for bean, and if not there, send user to user welcome page
		else if (!hasBean || requestURI.contains("/studentWelcome.html"))  
			forwardURL = studentWelcomeController.handleRequest(request, response);
		else if (requestURI.contains("/catalog.html"))
			forwardURL = catalogController.handleRequest(request, response);
		else if (requestURI.contains("/cart.html") || requestURI.endsWith("/updateItem")|| requestURI.endsWith("/removeItem"))
			forwardURL = cartController.handleRequest(request, response);
		else if (requestURI.contains("/product.html"))
			forwardURL = productController.handleRequest(request, response);
		else if (requestURI.contains("/invoice.html")) 
			forwardURL = invoiceController.handleRequest(request, response);
		else if (requestURI.contains("/listen.html")) 
			forwardURL = listenController.handleRequest(request, response);
		else if (requestURI.contains("/download.do"))   // download.html didn't work
			forwardURL = downloadController.handleRequest(request, response);
		else if (requestURI.contains("/register.html")) 
			forwardURL = registrationController.handleRequest(request, response); 
		else 
			throw new ServletException("DispatcherServlet: Unknown servlet path: "
					+ requestURL);

		System.out.println("DispatcherServlet: forwarding to "+ forwardURL);
	
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(forwardURL);
		
		
		dispatcher.forward(request, response);
		}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
