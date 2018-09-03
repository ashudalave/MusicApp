package cs636.music.presentation.web;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import cs636.music.service.AdminServiceAPI;
import cs636.music.service.CatalogServiceAPI;
import cs636.music.service.SalesServiceAPI;
import cs636.music.service.ServiceException;
import cs636.music.service.data.DownloadData;
import cs636.music.service.data.InvoiceData;

public class ReportController implements Controller {
	
	private SalesServiceAPI salesService;
	private CatalogServiceAPI catalogService;
	private String view;

	public ReportController (SalesServiceAPI salesService, CatalogServiceAPI catalogS, String view) {
		this.salesService = salesService;
		this.catalogService = catalogS;
		this.view = view;
	}

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Set<InvoiceData> invoices = new HashSet<InvoiceData>();
		Set<DownloadData> downloads = new HashSet<DownloadData>();
		try {
			invoices.addAll(salesService.getListofInvoices());
			downloads.addAll(catalogService.getListofDownloads());
		} catch (ServiceException e) {
			System.out.println("ReportController: " + e);
			throw new ServletException(e);
		}
		request.setAttribute("invoices", invoices);
		request.setAttribute("downloads", downloads);
		return view;
	}
}
