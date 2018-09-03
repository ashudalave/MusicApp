package cs636.music.presentation.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs636.music.domain.Invoice;
import cs636.music.service.CatalogServiceAPI;
import cs636.music.service.SalesServiceAPI;
import cs636.music.service.ServiceException;
import cs636.music.service.data.InvoiceData;

public class ProcessInvoiceController implements Controller {
	private SalesServiceAPI adminService;
	private String view;

	public ProcessInvoiceController(SalesServiceAPI adminService, String view) {
		this.adminService = adminService;
		this.view = view;
	}

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Set<InvoiceData> invoices = new HashSet<InvoiceData>();
		String invoiceIdToProcessString = (String) request.getParameter("invoiceIdToProcess");
		Integer invoiceIdToProcess = null;
		if (invoiceIdToProcessString!= null)
			invoiceIdToProcess = Integer.parseInt(invoiceIdToProcessString);
		try {
			if (invoiceIdToProcess != null)
				adminService.processInvoice(invoiceIdToProcess);
			invoices = new HashSet<InvoiceData>(adminService.getListofUnprocessedInvoices());
		} catch (ServiceException e) {
			System.out.println("ProcessInvoiceController: " + e);
			throw new ServletException(e);
		}
		request.setAttribute("invoices", invoices);
		return view;
	}
}
